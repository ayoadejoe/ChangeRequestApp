package com.iqjoy.changeapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Domains")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "domain", length = 200, nullable = false)
    private String domain;

    @Column(name = "time_created", nullable = false)
    private LocalDateTime timeCreated;

    @Column(name = "creator", length = 50)
    private String creator;

    @Column(name = "organization", length = 200)
    private String organization;

    @Column(name = "activate")
    private Boolean activate;

    @Column(name = "receiptno", length = 50)
    private String receiptNo;
    @ElementCollection
    @CollectionTable(name = "authorisation_text", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "authorisation", columnDefinition = "varchar(50)[]")
    private List<String> authorisation;
    @ElementCollection
    @CollectionTable(name = "company_approval_texts", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "approval_status_texts", columnDefinition = "varchar(20)[]")
    private List<String> approvalStatusTexts;
    @ElementCollection
    @CollectionTable(name = "teams_text", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "teams", columnDefinition = "varchar(20)[]")
    private List<String> teams;

    @ElementCollection
    @CollectionTable(name = "company_status_texts", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "change_status_texts", columnDefinition = "varchar(20)[]")
    private List<String> changeStatusTexts;

    @Column(name = "comments", length = 2147483647)
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public List<String> getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(List<String> authorisation) {
        this.authorisation = authorisation;
    }

    public List<String> getApprovalStatusTexts() {
        return approvalStatusTexts;
    }

    public void setApprovalStatusTexts(List<String> approvalStatusTexts) {
        this.approvalStatusTexts = approvalStatusTexts;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getChangeStatusTexts() {
        return changeStatusTexts;
    }

    public void setChangeStatusTexts(List<String> changeStatusTexts) {
        this.changeStatusTexts = changeStatusTexts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
