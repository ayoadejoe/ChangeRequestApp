package com.iqjoy.changeapp.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "login")
    private ZonedDateTime login;

    @Column(name = "role")
    private String role;

    @Column(name = "designation")
    private String designation;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "current")
    private boolean current;

    @ElementCollection
    @CollectionTable(name = "staff_notes", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "note")
    private List<String> notes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "staff_teams", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "team")
    private List<String> team = new ArrayList<>();


    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public ZonedDateTime getLogin() {
        return login;
    }

    public void setLogin(ZonedDateTime login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> groups) {
        this.team = groups;
    }

}
