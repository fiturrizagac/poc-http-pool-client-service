package com.frabef.httppoolclient.config;


import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(20);
        result.setDefaultMaxPerRoute(20);
        //result.setMaxPerRoute(new HttpRoute(new HttpHost("sleepy-escarpment-39046.herokuapp.com",443,"https")),20);
        return result;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
            .setConnectionRequestTimeout(3000)
            .setConnectTimeout(3000)
            .setSocketTimeout(3000)
            .build();
    }

    @Bean
    public HttpClient httpClient() {
        CloseableHttpClient result = HttpClientBuilder
            .create()
            .setConnectionManager(this.httpClientConnectionManager())
            .setDefaultRequestConfig(this.requestConfig())
            .build();
        return result;
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(this.httpClient());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

}
