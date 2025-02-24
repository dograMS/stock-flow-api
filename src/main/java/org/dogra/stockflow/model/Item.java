package org.dogra.stockflow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class Item {

    private Long id;

    private String name;

    private String baseUnitPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Party> providers = new HashSet<>();
}
