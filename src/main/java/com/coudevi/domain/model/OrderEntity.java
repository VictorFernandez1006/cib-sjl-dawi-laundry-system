package com.coudevi.domain.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="client_id", nullable=false)
    private ClientEntity client;

    @ManyToOne(optional=false) @JoinColumn(name="service_type_id", nullable=false)
    private ServiceTypeEntity serviceType;

    @Column(name="weight_kg", nullable=false, precision=10, scale=2)
    private BigDecimal weightKg;

    @Column(name="unit_price", nullable=false, precision=10, scale=2)
    private BigDecimal unitPrice;

    @Column(name="total_price", nullable=false, precision=10, scale=2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private OrderStatus status;

    @Column(length=300)
    private String notes;

    @ManyToOne @JoinColumn(name="assigned_to")
    private UserEntity assignedTo;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
}
