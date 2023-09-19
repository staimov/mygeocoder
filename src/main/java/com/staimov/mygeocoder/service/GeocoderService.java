package com.staimov.mygeocoder.service;

import com.staimov.mygeocoder.entity.Site;
import com.staimov.mygeocoder.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {
    private static final String KEY = "525bb79cab1da889fb69c92e496ceb52";
    private static final String BASE_URL = "http://api.positionstack.com";
    private static final String PATH = "/v1/forward";

    private final WebClient client;

    @Autowired
    public GeocoderService(
            WebClient.Builder clientBuilder) {
        client = clientBuilder.baseUrl(BASE_URL).build();
    }

    public Site updateSiteLatLng(Site site) {
        return getLatLng(site.getLabel());
    }

    public Site getLatLng(String... address) {
        String encodedAddress = Stream.of(address)
                //.map(s -> URLEncoder.encode(s, StandardCharsets.UTF_8))
                .collect(Collectors.joining(","));
        Response response = client.get()
                .uri(uriBuilder -> uriBuilder.path(PATH)
                    .queryParam("access_key", KEY)
                    .queryParam("query", encodedAddress)
                        .build()
                )
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));

        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return null;
        }

        return new Site(
                response.getData().get(0).getLabel(),
                response.getData().get(0).getLatitude(),
                response.getData().get(0).getLongitude());
    }
}
