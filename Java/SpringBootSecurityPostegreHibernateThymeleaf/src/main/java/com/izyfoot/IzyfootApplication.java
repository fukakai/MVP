/**
 * Application: IziFoot
 * Author: Romain DALICHAMP
 */
package com.izyfoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IzyfootApplication {

	public static void main(String[] args) {
		SpringApplication.run(IzyfootApplication.class, args);
        System.out.println("LOG - Application"); //to remove
	}

}
