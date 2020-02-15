package com.alithya.mvp.springvault;

import com.alithya.mvp.springvault.config.VaultConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfiguration.class)
public class SpringvaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringvaultApplication.class, args);
	}

}
