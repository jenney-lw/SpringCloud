package com.lw.springcloud.eurekaconsumer.feign.clients;

import com.lw.springcloud.eurekaconsumer.Config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "eureka-client", configuration = FeignMultipartSupportConfig.class)
public interface FileUploadFeignService {

    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile/server",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);

}
