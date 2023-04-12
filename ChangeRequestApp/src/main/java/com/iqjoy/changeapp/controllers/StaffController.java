package com.iqjoy.changeapp.controllers;

import com.iqjoy.changeapp.entities.Staff;
import com.iqjoy.changeapp.repository.StaffRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/crastaff/staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping("/processlogin")
    public String createUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String task) {


        String staff = staffRepository.findByEmail(email);

        if (staff != null) {
            return "User email already exists";
        }else{
            if(task.equals("register")){
                byte[] passwordDigest = getMD5Digest(password);

                Staff staffToSave = new Staff();
                staffToSave.setUsername(email);
                staffToSave.setPassword(passwordDigest);

                staffRepository.save(staffToSave);
                return "User created successfully";
            }else{
                return "This user email does not exist";
            }
        }


    }

    @PostMapping
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
