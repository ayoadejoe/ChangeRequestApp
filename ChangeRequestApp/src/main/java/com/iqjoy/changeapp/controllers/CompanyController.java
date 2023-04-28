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
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService CompanyService;

    @Autowired
    public CompanyController(CompanyService CompanyService) {
        this.CompanyService = CompanyService;
    }
    
    
    @PostMapping("/updatecompany")
    public ResponseEntity<CompanyEntity> updateAuthorisation(@RequestBody Map<String, Object> request) {
        String companyemail = (String) request.get("companyemail");
        List<String> authorisation = (List<String>) request.get("authorizations");
        List<String> approvalStatusTexts = (List<String>) request.get("approval");
        List<String> teams = (List<String>) request.get("teams");
        List<String> changeStatusTexts = (List<String>) request.get("changestatus");
        List<String> infrastructureTexts = (List<String>) request.get("infrastructure");
        System.out.println("infrastructure:"+infrastructureTexts);
        String[] mailsplit = companyemail.split("@");
        String domain = mailsplit[1];
        System.out.println("Domain requested:"+domain);

        CompanyEntity company = CompanyService.findByDomain(domain);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setAuthorisation(authorisation);
        company.setApprovalStatusTexts(approvalStatusTexts);
        company.setTeams(teams);
        company.setChangeStatusTexts(changeStatusTexts);
        company.setInfrastructureTexts(infrastructureTexts);
        CompanyService.saveCompany(company);

        return new ResponseEntity<CompanyEntity>(company, HttpStatus.OK);
    }

    @PostMapping("/getauthorization")
    public ResponseEntity<CompanyEntity> getAuthorisation(@RequestBody Map<String, String> request) {

        String companyemail = request.get("domain");
        String[] mailsplit = companyemail.split("@");
        String domain = mailsplit[1];
        System.out.println("Domain requested:"+domain);
        CompanyEntity Company = CompanyService.findByDomain(domain);

        if (Company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /*List<String>  authorization = Company.getAuthorisation();
        List<String> approvalStatusTexts = Company.getApprovalStatusTexts();
        List<String> teams = Company.getTeams();
        List<String> changeStatusTexts = Company.getChangeStatusTexts();*/

        return new ResponseEntity<CompanyEntity>(Company, HttpStatus.OK);
    }
}
