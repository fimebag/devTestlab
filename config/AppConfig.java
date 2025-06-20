package com.fimepay.merchantapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;


@Configuration
public class AppConfig {
@Bean
public RestTemplate restTemplate() {
    RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(2))
            .setResponseTimeout(Timeout.ofSeconds(5))
            .build();

    CloseableHttpClient client = HttpClients.custom()
        .setDefaultRequestConfig(config)
        .build();

    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
    return new RestTemplate(factory);
}
}
/*    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(2_000);   // 2s connect timeout
        factory.setReadTimeout(5_000);      // 5s read timeout
        return new RestTemplate(factory);
    }
} */