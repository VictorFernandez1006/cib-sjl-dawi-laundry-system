package com.coudevi.domain.repository;

import com.coudevi.domain.model.ServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceTypeEntity, Long> {
    List<ServiceTypeEntity> findAllByEnabledTrue();

}
