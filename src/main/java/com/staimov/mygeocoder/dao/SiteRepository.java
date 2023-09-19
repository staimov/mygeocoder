package com.staimov.mygeocoder.dao;

import com.staimov.mygeocoder.entity.Site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
}
