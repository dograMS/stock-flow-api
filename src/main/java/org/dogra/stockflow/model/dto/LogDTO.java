package org.dogra.stockflow.model.dto;

import org.dogra.stockflow.model.Log;

import java.io.Serializable;

public class LogDTO implements Serializable {

    private final Long id;
    private final String logDate;
    private final Long item_id;
    private final String itemName;
    private final Long party_id;
    private final String partyName;
    private final Long staff_id;
    private final String staffName;
    private final Long quantity;
    private final Long unitPrice;
    private final String transactionType;

    public LogDTO(Log log) {
        this.id = log.getId();
        this.logDate = log.getLogDate().toString();
        this.item_id = log.getItem().getId();
        this.itemName = log.getItem().getName();
        this.party_id = log.getParty().getId();
        this.partyName = log.getParty().getName();
        this.staff_id = log.getStaff().getId();
        this.staffName = log.getStaff().getUsername();
        this.quantity = log.getQuantity();
        this.unitPrice = log.getUnitPrice();
        this.transactionType = log.getType().toString();
    }

}
