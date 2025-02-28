package org.dogra.stockflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Party { // party represents an entity that either provide or consume items

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[0-9]{10}", message = "Invalid phone number")
    private String phone;

    @Column(nullable = false)
    private String company;

    @Email(message = "Invalid email address format")
    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private String addressLine;

}
