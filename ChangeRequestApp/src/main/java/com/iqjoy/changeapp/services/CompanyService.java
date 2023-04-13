package com.iqjoy.changeapp.services;

import com.iqjoy.changeapp.entities.CompanyEntity;
import com.iqjoy.changeapp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository CompanyRepository;

    @Autowired
    public CompanyService(CompanyRepository CompanyRepository) {
        this.CompanyRepository = CompanyRepository;
    }

    public void saveCompany(CompanyEntity Company) {
        CompanyRepository.save(Company);
    }

    public CompanyEntity findByDomain(String domain) {
        return CompanyRepository.findByDomain(domain);
    }

    // other methods...
}
