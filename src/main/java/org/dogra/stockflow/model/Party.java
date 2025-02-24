package org.dogra.stockflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Pattern(regexp = "[0-9]{10}")
    private String phone;

    @Column(nullable = false)
    private String Company;

    @Email
    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String addressLine;

}
