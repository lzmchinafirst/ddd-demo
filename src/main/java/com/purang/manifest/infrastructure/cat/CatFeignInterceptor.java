package com.purang.manifest.infrastructure.cat;

import com.dianping.cat.Cat;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class CatFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        boolean flag = requestAttributes != null && ServletRequestAttributes.class.isInstance(requestAttributes) && ((ServletRequestAttributes) requestAttributes).getRequest() != null;
        if (flag && null != (((ServletRequestAttributes) requestAttributes).getRequest()).getAttribute(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID)) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, (String) request.getAttribute(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID));
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, (String) request.getAttribute(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID));
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, (String) request.getAttribute(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID));
        } else {
            CatMsgContext catContext = new CatMsgContext();
            Cat.logRemoteCallClient(catContext, Cat.getManager().getDomain());
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, catContext.getProperty(Cat.Context.ROOT));
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, catContext.getProperty(Cat.Context.PARENT));
            requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, catContext.getProperty(Cat.Context.CHILD));
        }
    }
}
