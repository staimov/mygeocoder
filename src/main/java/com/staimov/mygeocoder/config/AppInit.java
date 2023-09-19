package com.staimov.mygeocoder.config;

import com.staimov.mygeocoder.service.SiteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final SiteService service;

    public AppInit(SiteService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        service.initializeDatabase();
    }
}
