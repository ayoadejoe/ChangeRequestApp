package com.iqjoy.changeapp.controllers;

import com.iqjoy.changeapp.entities.Staff;
import com.iqjoy.changeapp.repository.StaffRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping("/createuser")
    public String createUser(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String companyemail = request.get("companyemail");
        String password = request.get("password");

        String[] namer = name.trim().split(" ");
        if(namer.length>1) {
            String firstname = namer[0];
            String lastname = namer[1];

            System.out.println("Username to register received:" + request);
            Staff staff = staffRepository.findByUsername(companyemail);

            if (staff != null) {
                System.out.println("This user already exists:" + companyemail);
                return "User email already exists";
            } else {
                byte[] passwordDigest = getMD5Digest(password);

                Staff staffToSave = new Staff();
                staffToSave.setUsername(companyemail);
                staffToSave.setPassword(passwordDigest);
                staffToSave.setFirstname(firstname);
                staffToSave.setLastname(lastname);
                ZonedDateTime currentTime = ZonedDateTime.now();
                System.out.println("current time:"+currentTime);
                staffToSave.setLogin(currentTime);

                staffRepository.save(staffToSave);
                return "User created successfully";
            }
        }else{
            return "Please enter your firstname and lastname";
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody Map<String, String> request) {
        String companyemail = request.get("companyemail");
        String password = request.get("password");

        System.out.println("Username to check received:"+companyemail);
        Staff staff = staffRepository.findByUsername(companyemail);
        if (staff == null) {
            // Return an error response if user not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Get the hash of the provided password
        byte[] passwordHash = getMD5Digest(password);

        // Compare the password hash with the hash stored in the staff object
        if (!Arrays.equals(passwordHash, staff.getPassword())) {
            // Return an error response if password is incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Return the staff object if authentication is successful
        return ResponseEntity.ok(staff);
    }


    @PostMapping("/savestaff")
    public Staff createStaff(@RequestBody Staff staff) {
        System.out.println("staff received:"+staff);
        return staffRepository.save(staff);
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Integer id) {
        return staffRepository.findById(id).orElse(null);
    }


    private byte[] getMD5Digest(String password) {
        return DigestUtils.md5(password);
    }
}
