package com.alithya.mvp.springvault.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("springvault")
public class VaultConfiguration {
    private String username = "";
    private String password = "";
    private String url = "";
}