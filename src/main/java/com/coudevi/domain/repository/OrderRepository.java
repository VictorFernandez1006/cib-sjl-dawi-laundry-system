package com.coudevi.domain.repository;

import com.coudevi.domain.model.ClientEntity;
import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    List<OrderEntity> findByClient(ClientEntity client);
    List<OrderEntity> findByStatus(OrderStatus status);
    List<OrderEntity> findByStatusAndAssignedToIsNull(OrderStatus status);
    List<OrderEntity> findByAssignedTo_IdAndStatusIn(Long workerUserId, Collection<OrderStatus> statuses);
    boolean existsByIdAndStatusAndAssignedToIsNull(Long id, OrderStatus status);
}
