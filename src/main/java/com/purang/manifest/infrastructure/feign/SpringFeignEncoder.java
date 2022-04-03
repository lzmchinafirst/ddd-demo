package com.purang.manifest.infrastructure.feign;

import feign.Request;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class SpringFeignEncoder extends FormEncoder {
    private final List<HttpMessageConverter<?>> converters = new RestTemplate().getMessageConverters();
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public SpringFeignEncoder() {
        this(new Default());
    }

    public SpringFeignEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) this.getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        Map<String, Object> data;
        if (bodyType.equals(MultipartFile[].class)) {
            MultipartFile[] files = (MultipartFile[]) object;
            data = new HashMap(files.length, 1.0F);
            if (files != null) {
                data.put(files.length == 0 ? "" : files[0].getName(), files);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            MultipartFile file = (MultipartFile) object;
            data = Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (this.isMultipartFileCollection(object)) {
            Iterable<?> iterable = (Iterable) object;
            data = new HashMap();
            Iterator var13 = iterable.iterator();
            while (var13.hasNext()) {
                Object item = var13.next();
                MultipartFile file = (MultipartFile) item;
                data.put(file.getName(), file);
            }

            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (MAP_STRING_WILDCARD.equals(bodyType)) {
            final HttpHeaders multipartHeaders = new HttpHeaders();
            final HttpHeaders jsonHeaders = new HttpHeaders();
            multipartHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
            encodeMultipartFormRequest((Map<Object, ?>) object, multipartHeaders, template);
        } else {
            super.encode(object, bodyType, template);
        }

    }

    /**
     * Encodes the request as a multipart form. It can detect a single {@link MultipartFile}, an
     * array of {@link MultipartFile}s, or POJOs (that are converted to JSON).
     *
     * @param formMap
     * @param template
     * @throws EncodeException
     */
    private void encodeMultipartFormRequest(Map<Object, ?> formMap, HttpHeaders multipartHeaders, RequestTemplate template) throws EncodeException {
        if (formMap == null) {
            throw new EncodeException("Cannot encode request with null form.");
        }
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        for (Entry<Object, ?> entry : formMap.entrySet()) {
            Object value = entry.getValue();
            if (isMultipartFile(value)) {
                map.add(entry.getKey(), encodeMultipartFile((MultipartFile) value));
            } else if (isMultipartFileArray(value)) {
                encodeMultipartFiles(map, (String) entry.getKey(), Arrays.asList((MultipartFile[]) value));
            } else {
                map.add(entry.getKey(), encodeJsonObject(value));
            }
        }
        encodeRequest(map, multipartHeaders, template);
    }

    private boolean isMultipartFile(Object object) {
        return object instanceof MultipartFile;
    }

    private boolean isMultipartFileArray(Object o) {
        return o != null && o.getClass().isArray() && MultipartFile.class.isAssignableFrom(o.getClass().getComponentType());
    }

    /**
     * Wraps a single {@link MultipartFile} into a {@link HttpEntity} and sets the
     * {@code Content-type} header to {@code application/octet-stream}
     *
     * @param file
     * @return
     */
    private HttpEntity<?> encodeMultipartFile(MultipartFile file) {
        HttpHeaders filePartHeaders = new HttpHeaders();
        filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            Resource multipartFileResource = new MultipartFileResource(file.getOriginalFilename(), file.getSize(), file.getInputStream());
            return new HttpEntity<>(multipartFileResource, filePartHeaders);
        } catch (IOException ex) {
            throw new EncodeException("Cannot encode request.", ex);
        }
    }

    /**
     * Fills the request map with {@link HttpEntity}s containing the given {@link MultipartFile}s.
     * Sets the {@code Content-type} header to {@code application/octet-stream} for each file.
     *
     * @param map   current request map.
     * @param name  the name of the array field in the multipart form.
     * @param files
     */
    private void encodeMultipartFiles(LinkedMultiValueMap<Object, Object> map, String name, List<? extends MultipartFile> files) {
        HttpHeaders filePartHeaders = new HttpHeaders();
        filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            for (MultipartFile file : files) {
                Resource multipartFileResource = new MultipartFileResource(file.getOriginalFilename(), file.getSize(), file.getInputStream());
                map.add(name, new HttpEntity<>(multipartFileResource, filePartHeaders));
            }
        } catch (IOException ex) {
            throw new EncodeException("Cannot encode request.", ex);
        }
    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        } else {
            Iterable<?> iterable = (Iterable) object;
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext() && iterator.next() instanceof MultipartFile;
        }
    }

    /**
     * Wraps an object into a {@link HttpEntity} and sets the {@code Content-type} header to
     * {@code application/json}
     *
     * @param o
     * @return
     */
    private HttpEntity<?> encodeJsonObject(Object o) {
        HttpHeaders jsonPartHeaders = new HttpHeaders();
        jsonPartHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(o, jsonPartHeaders);
    }

    /**
     * Calls the conversion chain actually used by
     * {@link RestTemplate}, filling with the body of the request
     * template.
     *
     * @param value
     * @param requestHeaders
     * @param template
     * @throws EncodeException
     */
    private void encodeRequest(Object value, HttpHeaders requestHeaders, RequestTemplate template) throws EncodeException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpOutputMessage dummyRequest = new HttpOutputMessageImpl(outputStream, requestHeaders);
        try {
            Class<?> requestType = value.getClass();
            MediaType requestContentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> messageConverter : converters) {
                if (messageConverter.canWrite(requestType, requestContentType)) {
                    ((HttpMessageConverter<Object>) messageConverter).write(
                            value, requestContentType, dummyRequest);
                    break;
                }
            }
        } catch (IOException ex) {
            throw new EncodeException("Cannot encode request.", ex);
        }
        HttpHeaders headers = dummyRequest.getHeaders();
        if (headers != null) {
            for (Entry<String, List<String>> entry : headers.entrySet()) {
                template.header(entry.getKey(), entry.getValue());
            }
        }
        /*
        we should use a template output stream... this will cause issues if files are too big,
        since the whole request will be in memory.
         */
        template.body(Request.Body.encoded(outputStream.toByteArray(), UTF_8));
    }

    /**
     * Minimal implementation of {@link HttpOutputMessage}. It's needed to
     * provide the request body output stream to
     * {@link HttpMessageConverter}
     */
    private class HttpOutputMessageImpl implements HttpOutputMessage {

        private final OutputStream body;
        private final HttpHeaders headers;

        public HttpOutputMessageImpl(OutputStream body, HttpHeaders headers) {
            this.body = body;
            this.headers = headers;
        }

        @Override
        public OutputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

    }

    /**
     * Dummy resource class. Wraps file content and its original name.
     */
    static class MultipartFileResource extends InputStreamResource {

        private final String filename;
        private final long size;

        public MultipartFileResource(String filename, long size, InputStream inputStream) {
            super(inputStream);
            this.size = size;
            this.filename = filename;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public InputStream getInputStream() throws IOException, IllegalStateException {
            return super.getInputStream(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public long contentLength() {
            return size;
        }
    }
}
