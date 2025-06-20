// src/main/java/com/fimepay/merchantapp/config/WebMvcConfig.java
package com.fimepay.merchantapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${fimepay.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /logos/** → file system path
        registry.addResourceHandler("/logos/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
