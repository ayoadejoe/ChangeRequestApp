package com.iqjoy.changeapp.controllers;

import com.iqjoy.changeapp.StaffCheck;
import com.iqjoy.changeapp.entities.ChangeRequestEntity;
import com.iqjoy.changeapp.repository.ChangeRequestRepository;
import com.iqjoy.changeapp.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/changerequests")
public class ChangeRequestController {

    @Autowired
    private ChangeRequestRepository changeRequestRepository;

    @Autowired
    private StaffRepository staffRepository;

    // Get all change requests
    @PostMapping ("/selected-changes")
    public List<ChangeRequestEntity> getSelectedChangeRequests(@RequestBody Map<String, Object> request) {

        List<String> teams = (List<String>) request.get("teams");
        String role = (String) request.get("role");

        List<ChangeRequestEntity> resultSet = new ArrayList<>();

        if(role.equals("Administrator")){
            System.out.println("Result---X");
            resultSet = changeRequestRepository.findAll();

        }else{
            System.out.println("Result---Y");
            List<ChangeRequestEntity> rows = changeRequestRepository.findAll();

            for(String team : teams){
                // filter allChangeRequests where selectedteams contains the current team
                resultSet.addAll(rows.stream()
                        .filter(cr -> cr.getTeam().contains(team))
                        .collect(Collectors.toSet()));
            }
        }
        resultSet.sort(Comparator.comparing(ChangeRequestEntity::getRequestCreated).reversed());
        return resultSet;
    }

    // Get all change requests
    @PostMapping ("/all-changes")
    public List<ChangeRequestEntity> getAllChangeRequests(@RequestBody Map<String, Object> request) {
        List<ChangeRequestEntity> resultSet = new ArrayList<>();
        String username = request.get("username").toString();
        String password = request.get("password").toString();

        System.out.println(username);

        if(new StaffCheck().authenticateUser(request.get("username").toString(), request.get("password").toString(), staffRepository)){
            resultSet = changeRequestRepository.findAll();
            //resultSet.forEach(System.out::println);
            resultSet.sort(Comparator.comparing(ChangeRequestEntity::getRequestCreated).reversed());

        }else{
            System.out.println("false:"+resultSet);
        }

        return resultSet;
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