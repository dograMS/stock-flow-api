package org.dogra.stockflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Item item;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Party provider;

    @Column(nullable = false)
    @Min(value = 0, message = "stock cannot be neagative")
    private Long currentStock;

    @Column(nullable = false)
    @Min(value = 0, message = "stock cannot be neagative")
    private Long minimumStock;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;
}
