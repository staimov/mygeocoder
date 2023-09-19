package com.staimov.mygeocoder.service;

import com.staimov.mygeocoder.entity.Site;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeocoderServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(GeocoderServiceTest.class);

    @Autowired
    private GeocoderService service;

    @Test
    void gatLatLngWithoutStreet() {
        Site site = service.getLatLng("Boston", "MA", "USA");
        logger.info(site.toString());
        assertAll(
                () -> assertEquals(42.36, site.getLatitude(), 0.02),
                () -> assertEquals(-71.05, site.getLongitude(), 0.02)
        );
    }

    @Test
    void gatLatLngWithStreet() {
        Site site = service.getLatLng(
                "1600 Amphitheatre Parkway", "Mountain View", "CA", "USA");
        logger.info(site.toString());
        assertAll(
                () -> assertEquals(37.42, site.getLatitude(), 0.02),
                () -> assertEquals(-122.09, site.getLongitude(), 0.02)
        );
    }

}
