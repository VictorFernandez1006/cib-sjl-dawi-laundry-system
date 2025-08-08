package com.coudevi.domain.repository;

import com.coudevi.domain.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByDocumentId(String documentId);
    boolean existsByEmail(String email);
    Optional<ClientEntity> findByDocumentId(String documentId);
}
