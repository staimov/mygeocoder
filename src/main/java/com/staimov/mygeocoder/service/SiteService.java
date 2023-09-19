package com.staimov.mygeocoder.service;

import com.staimov.mygeocoder.dao.SiteRepository;
import com.staimov.mygeocoder.entity.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SiteService {
    private final SiteRepository repository;
    private final GeocoderService geocoderService;

    @Autowired
    public SiteService(SiteRepository repository, GeocoderService geocoderService) {
        this.repository = repository;
        this.geocoderService = geocoderService;
    }

    private Site fillInLatLng(Site site) {
        return geocoderService.updateSiteLatLng(site);
    }

    public void initializeDatabase() {
        repository.saveAll(
                List.of(
                        fillInLatLng(new Site("Boston, MA, USA")),
                        fillInLatLng(new Site("Magnitogorsk, RUS")),
                        fillInLatLng(new Site("Prague, CZE")))
        ).forEach(System.out::println);
    }

    public List<Site> getAllSites() {
        return repository.findAll();
    }

    public Optional<Site> findSiteById(Integer id) {
        return repository.findById(id);
    }

    public Site saveSite(String name) {
        return repository.save(fillInLatLng(new Site(name)));
    }
}
