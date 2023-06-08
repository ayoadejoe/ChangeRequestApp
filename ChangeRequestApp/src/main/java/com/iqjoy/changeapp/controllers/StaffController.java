package com.iqjoy.changeapp.controllers;

import com.iqjoy.changeapp.entities.CompanyEntity;
import com.iqjoy.changeapp.entities.StaffEntity;
import com.iqjoy.changeapp.repository.StaffRepository;
import com.iqjoy.changeapp.services.CompanyService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    private final CompanyService CompanyService;

    @Autowired
    public StaffController(CompanyService CompanyService) {
        this.CompanyService = CompanyService;
    }


    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody Map<String, String> request) {
        String companyemail = request.get("companyemail");
        String passwordSent = request.get("password");

        System.out.println("Username to check received:"+companyemail);
        StaffEntity staffEntity = staffRepository.findByUsername(companyemail);
        if (staffEntity == null) {
            // Return an error response if user not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String encodedPassword = staffEntity.getPassword();

        if(!passDecrypt(passwordSent, encodedPassword)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        return ResponseEntity.ok(staffEntity);
    }

    @CrossOrigin
    @PostMapping("/savestaff")
    public ResponseEntity<Object> createStaff(@RequestBody StaffEntity staffEntity) {
        Arrays.stream(StaffEntity.class.getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(staffEntity);
                        System.out.println(field.getName() + ": " + value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();

                    }
                });
        String username = staffEntity.getUsername();
        System.out.println("staff received:"+ username );


        if (staffRepository.findByUsername(username) != null) {
            System.out.println("staff already in database:"+ username );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email already registered");
        }

        try {
            String[] mailsplit = username.split("@");
            String domain = mailsplit[1];
            System.out.println("Domain requested:" + domain);

            CompanyEntity company = CompanyService.findByDomain(domain);

            if (company == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Organization");
            }

            String passwordSent = staffEntity.getPassword();
            passwordSent = passEncrypt(passwordSent);
            staffEntity.setPassword(passwordSent);

            String firstName = staffEntity.getFirstname().substring(0, 1).toUpperCase() + staffEntity.getFirstname().substring(1);
            String lastName = staffEntity.getLastname().substring(0, 1).toUpperCase() + staffEntity.getLastname().substring(1);

            staffEntity.setFirstname(firstName);
            staffEntity.setLastname(lastName);

            staffRepository.save(staffEntity);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Organization");
        }
        return ResponseEntity.ok(staffEntity);
    }
    @CrossOrigin
    @GetMapping("/companyemail")
    public StaffEntity getStaffById(@PathVariable Integer id) {
        return staffRepository.findById(id).orElse(null);
    }


    private String passEncrypt(String pass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(pass);
        System.out.println(encodedPassword);
        return encodedPassword;
    }

    private boolean passDecrypt(String originalPassword, String encodedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(originalPassword, encodedPassword);
        System.out.println(encodedPassword+" is "+isPasswordMatch); // should print true

        return isPasswordMatch;
    }

}


        /*/ Compare the password hash with the hash stored in the staff object
        // Get the hash of the provided password
        byte[] passwordHash = getMD5Digest(password);

        if (!Arrays.equals(passwordHash, staffEntity.getPassword())) {
            // Return an error response if password is incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }else{
            System.out.println(passwordHash+" -- "+staffEntity.getPassword());
        }*/

// Return the staff object if authentication is successful