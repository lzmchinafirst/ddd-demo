package com.purang.manifest.infrastructure.remote;

import com.alibaba.fastjson.JSONObject;
import com.purang.manifest.infrastructure.cat.CatFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(url = "${crm-customer-service.host}", value = "crm-customer-service", configuration = CatFeignInterceptor.class)
public interface CrmRemoteService {

    @PostMapping(value = "/crm/customer/query_customer_info.html")
    JSONObject listCustomerApplyForPc(JSONObject params);
}
