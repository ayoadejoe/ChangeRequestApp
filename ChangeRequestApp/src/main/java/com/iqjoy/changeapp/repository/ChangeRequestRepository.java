package com.iqjoy.changeapp.repository;
import com.iqjoy.changeapp.entities.ChangeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequestEntity, Long> {

}
