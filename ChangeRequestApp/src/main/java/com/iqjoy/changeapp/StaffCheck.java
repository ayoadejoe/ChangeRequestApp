package com.iqjoy.changeapp;

import com.iqjoy.changeapp.entities.StaffEntity;
import com.iqjoy.changeapp.repository.StaffRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class StaffCheck {

    public boolean authenticateUser(String username, String password, StaffRepository staffRepository ){
        StaffEntity staffEntity = staffRepository.findByUsername(username);
        if (staffEntity == null) {
            // Return an error response if user not found
            return false;
        }

        String encodedPassword = staffEntity.getPassword();

        if(passDecrypt(password, encodedPassword)){
            return true;
        }

        return false;
    }

    private boolean passDecrypt(String originalPassword, String encodedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(originalPassword, encodedPassword);
        System.out.println(encodedPassword+" is "+isPasswordMatch); // should print true

        return isPasswordMatch;
    }

}
