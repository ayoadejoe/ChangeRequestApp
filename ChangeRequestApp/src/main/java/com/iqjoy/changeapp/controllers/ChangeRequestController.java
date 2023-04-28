package com.iqjoy.changeapp.controllers;
import com.iqjoy.changeapp.entities.ChangeRequestEntity;
import com.iqjoy.changeapp.repository.ChangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/changerequests")
public class ChangeRequestController {

    @Autowired
    private ChangeRequestRepository changeRequestRepository;

    // Get all change requests
    @GetMapping("/all")
    public List<ChangeRequestEntity> getAllChangeRequests() {
        return changeRequestRepository.findAll();
    }

    // Get a single change request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChangeRequestEntity> getChangeRequestById(@PathVariable Long id) {
        ChangeRequestEntity changeRequestEntity = changeRequestRepository.findById(id).orElse(null);
        if (changeRequestEntity != null) {
            return new ResponseEntity<>(changeRequestEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new change request
    @PostMapping("/create")
    public ChangeRequestEntity createChangeRequest(@RequestBody ChangeRequestEntity changeRequest) {
        Arrays.stream(ChangeRequestEntity.class.getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(changeRequest);
                        System.out.println(field.getName() + ": " + value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        ChangeRequestEntity changeRequestEntity = new ChangeRequestEntity();
        changeRequestEntity.setUsername(changeRequest.getUsername());
        changeRequestEntity.setRequestDescription(changeRequest.getRequestDescription());
        changeRequestEntity.setRequestTitle(changeRequest.getRequestTitle());
        changeRequestEntity.setRequestCreated(changeRequest.getRequestCreated());
        changeRequestEntity.setChangeDetails(changeRequest.getChangeDetails());
        changeRequestEntity.setChangeUpdates(changeRequest.getChangeUpdates());
        changeRequestEntity.setApprovalUpdates(changeRequest.getApprovalUpdates());
        changeRequestEntity.setApprovalTime(changeRequest.getApprovalTime());
        changeRequestEntity.setTeam(changeRequest.getTeam());
        changeRequestEntity.setComments(changeRequest.getComments());
        changeRequestEntity.setDateStart(changeRequest.getDateStart());
        changeRequestEntity.setDateEnd(changeRequest.getDateEnd());
        changeRequestEntity.setInfrastructure(changeRequest.getInfrastructure());
        changeRequestEntity.setImages(changeRequest.getImages());
        changeRequestEntity.setApprovers(changeRequest.getApprovers());
        changeRequestEntity.setRequestStatus(changeRequest.getRequestStatus());

        return changeRequestRepository.save(changeRequestEntity);
    }

    // Update an existing change request by ID
    @PutMapping("/{id}")
    public ResponseEntity<ChangeRequestEntity> updateChangeRequest(@PathVariable Long id, @RequestBody ChangeRequestEntity changeRequest) {
        ChangeRequestEntity existingChangeRequestEntity = changeRequestRepository.findById(id).orElse(null);
        if (existingChangeRequestEntity != null) {
            existingChangeRequestEntity.setUsername(changeRequest.getUsername());
            existingChangeRequestEntity.setRequestDescription(changeRequest.getRequestDescription());
            existingChangeRequestEntity.setRequestTitle(changeRequest.getRequestTitle());
            existingChangeRequestEntity.setApprovalTime(changeRequest.getApprovalTime());
            existingChangeRequestEntity.setChangeDetails(changeRequest.getChangeDetails());
            existingChangeRequestEntity.setChangeUpdates(changeRequest.getChangeUpdates());
            existingChangeRequestEntity.setApprovalUpdates(changeRequest.getApprovalUpdates());
            existingChangeRequestEntity.setTeam(changeRequest.getTeam());
            existingChangeRequestEntity.setComments(changeRequest.getComments());
            existingChangeRequestEntity.setDateStart(changeRequest.getDateStart());
            existingChangeRequestEntity.setDateEnd(changeRequest.getDateEnd());
            existingChangeRequestEntity.setInfrastructure(changeRequest.getInfrastructure());
            existingChangeRequestEntity.setImages(changeRequest.getImages());
            existingChangeRequestEntity.setApprovers(changeRequest.getApprovers());
            existingChangeRequestEntity.setRequestStatus(changeRequest.getRequestStatus());

            return new ResponseEntity<>(changeRequestRepository.save(existingChangeRequestEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an existing change request by ID
    // Delete an existing change request by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChangeRequest(@PathVariable Long id) {
        try {
            changeRequestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}