package com.miage.bibal;

import com.miage.bibal.api.GestionAPI;
import com.miage.bibal.api.OeuvreManagement;
import com.miage.bibal.api.UserManagementAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 *
 * @author alex
 */
@SpringBootApplication
public class Bibal extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        SpringApplication.run(UserManagementAPI.class, args);
        SpringApplication.run(OeuvreManagement.class, args);
        SpringApplication.run(GestionAPI.class, args);      
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
    private static Class<Bibal> applicationClass = Bibal.class;
}
