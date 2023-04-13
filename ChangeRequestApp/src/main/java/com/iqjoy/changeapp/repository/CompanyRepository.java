package com.iqjoy.changeapp.repository;


import com.iqjoy.changeapp.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    CompanyEntity findByDomain(String domain);
}
