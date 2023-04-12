package com.iqjoy.changeapp.repository;

import com.iqjoy.changeapp.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    String findByEmail(String email);
}
