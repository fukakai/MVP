package fr.dalichamp.microservices.gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient //Enable eureka client. It inherits from @EnableDiscoveryClient.
@EnableCircuitBreaker 	// Enable circuit breakers
public class SpringEurekaGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaGalleryApplication.class, args);
	}
	
	@Configuration
	class RestTemplateConfig {
		
		// Create a bean for restTemplate to call services
		// So, we can use the service name (like image-service) instead of localhost:port
		@Bean
		@LoadBalanced		// Load balance between service instances running at different ports.
		public RestTemplate restTemplate() {
		    return new RestTemplate();
		}
	}
}
