package org.dogra.stockflow.model.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dogra.stockflow.model.Item;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @Valid
    private Item item;
    private List<Long> itemProviderIdsList;
}
