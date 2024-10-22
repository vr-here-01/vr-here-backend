package com.vr.user.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.user.service.entities.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

}
