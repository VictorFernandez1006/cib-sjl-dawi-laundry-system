package com.coudevi.domain.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceTypeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(name="price_per_kg", nullable=false, precision=10, scale=2)
    private BigDecimal pricePerKg;

    @Column(nullable=false)
    private Boolean enabled = true;
}
