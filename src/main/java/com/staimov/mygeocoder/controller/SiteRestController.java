package com.staimov.mygeocoder.controller;

import com.staimov.mygeocoder.entity.Site;
import com.staimov.mygeocoder.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteRestController {
    private final SiteService service;

    @Autowired
    public SiteRestController(SiteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Site> findAll() {
        return service.getAllSites();
    }

    @GetMapping("{id}")
    public ResponseEntity<Site> findById(@PathVariable Integer id) {
        return ResponseEntity.of(service.findSiteById(id));
    }

    @PostMapping
    public ResponseEntity<Site> saveAstronaut(@RequestParam String address) {
        Site site = service.saveSite(address);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(site.getId())
                .toUri();
        return ResponseEntity.created(uri).body(site);
    }
}
