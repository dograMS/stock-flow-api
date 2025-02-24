package org.dogra.stockflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime logDate;

    @ManyToOne
    private Item item_id;

    @ManyToOne
    private Party party_id;

    @ManyToOne
    private Staff staff_id;

    @Column(nullable = false)
    @Size(min = 1)
    private Long quantity;

    @Column(nullable = false)
    @Size(min = 1)
    private Long unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

}


