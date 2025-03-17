package org.dogra.stockflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime logDate;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Party party;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Staff staff;

    @Column(nullable = false)
    @PositiveOrZero(message = "Value cannot be Negative")
    private Long quantity;

    @Column(nullable = false)
    @Positive(message = "Value Must be positive")
    private Long unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    public Log(LocalDateTime logDate, Item item, Party party, Staff staff, Long quantity, Long unitPrice,
               TransactionType type){
        this.logDate = logDate;
        this.item = item;
        this.party = party;
        this.staff = staff;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.type = type;
    }

}


