package com.iqjoy.changeapp.entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "crt")
public class ChangeRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "request_description", nullable = false)
    private String requestDescription;

    @Column(name = "request_title", nullable = false)
    private String requestTitle;

    @Column(name = "request_created", nullable = false)
    private LocalDateTime requestCreated;

    @Column(name = "approval_time")
    private LocalDateTime approvalTime;

    @Column(name = "change_details")
    private String changeDetails;

    @ElementCollection
    @CollectionTable(name = "change_updates", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "change_updates")
    private List<String> changeUpdates;

    @ElementCollection
    @CollectionTable(name = "approval_updates", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "approval_updates")
    private List<String> approvalUpdates;

    @ElementCollection
    @CollectionTable(name = "team", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "team")
    private List<String> team;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "infrastructure")
    private String infrastructure;

    @Lob
    @Column(name = "images")
    private byte[] images;

    @Column(name = "approvers")
    private String approvers;

    @Column(name = "request_status")
    private String requestStatus;



    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public LocalDateTime getRequestCreated() {
        return requestCreated;
    }

    public void setRequestCreated(LocalDateTime requestCreated) {
        this.requestCreated = requestCreated;
    }

    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getChangeDetails() {
        return changeDetails;
    }

    public void setChangeDetails(String changeDetails) {
        this.changeDetails = changeDetails;
    }

    public List<String> getChangeUpdates() {
        return changeUpdates;
    }

    public void setChangeUpdates(List<String> changeUpdates) {
        this.changeUpdates = changeUpdates;
    }

    public List<String> getApprovalUpdates() {
        return approvalUpdates;
    }

    public void setApprovalUpdates(List<String> approvalUpdates) {
        this.approvalUpdates = approvalUpdates;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
