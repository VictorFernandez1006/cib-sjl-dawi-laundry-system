package com.coudevi.application.spec;

import com.coudevi.domain.model.OrderEntity;
import com.coudevi.domain.model.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecifications {

    public static Specification<OrderEntity> byClientId(Long clientId) {
        return (root, q, cb) -> cb.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<OrderEntity> createdFrom(LocalDateTime from) {
        return (root, q, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), from);
    }

    public static Specification<OrderEntity> createdTo(LocalDateTime to) {
        return (root, q, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), to);
    }

    public static Specification<OrderEntity> byStatus(OrderStatus status) {
        return (root, q, cb) -> cb.equal(root.get("status"), status);
    }
}
