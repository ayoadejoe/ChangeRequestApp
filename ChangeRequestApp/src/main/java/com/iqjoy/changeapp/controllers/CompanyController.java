package com.iqjoy.changeapp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iqjoy.changeapp.services.CompanyService;
import com.iqjoy.changeapp.entities.CompanyEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService CompanyService;

    @Autowired
    public CompanyController(CompanyService CompanyService) {
        this.CompanyService = CompanyService;
    }
    
    
    @PostMapping("/authorisation")
    public ResponseEntity<CompanyEntity> updateAuthorisation(@RequestBody Map<String, List<String>> request) {
        List<String> domainList = request.get("domain");
        String domain = domainList.get(0);

        List<String> authorisation = request.get("Authorization");
        List<String> approvalStatusTexts = request.get("ApprovalStatusTexts");
        List<String> teams = request.get("Teams");
        List<String> changeStatusTexts = request.get("ChangeStatusTexts");

        CompanyEntity Company = CompanyService.findByDomain(domain);

        if (Company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Company.setAuthorisation(authorisation);
        CompanyService.saveCompany(Company);

        Company.setApprovalStatusTexts(approvalStatusTexts);
        CompanyService.saveCompany(Company);

        Company.setTeams(teams);
        CompanyService.saveCompany(Company);


        Company.setChangeStatusTexts(changeStatusTexts);
        CompanyService.saveCompany(Company);

        return new ResponseEntity<CompanyEntity>(Company, HttpStatus.OK);
    }

    // other methods...
}
