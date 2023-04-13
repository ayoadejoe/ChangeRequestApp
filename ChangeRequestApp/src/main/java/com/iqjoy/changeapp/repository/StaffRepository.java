package com.iqjoy.changeapp.repository;

import com.iqjoy.changeapp.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

    StaffEntity findByUsername(String companyemail);
}
