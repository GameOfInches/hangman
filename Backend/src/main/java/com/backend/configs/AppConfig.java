/**

 * File: AppConfig.java

 * Author: Krasimir Konstantinov

 * Date: 28/11/2023

 */
package com.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
