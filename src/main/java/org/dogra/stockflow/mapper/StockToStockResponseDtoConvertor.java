package org.dogra.stockflow.mapper;

import org.dogra.stockflow.model.Stock;
import org.dogra.stockflow.model.dto.StockResponseDTO;
import org.springframework.core.convert.converter.Converter;

public class StockToStockResponseDtoConvertor implements Converter<Stock, StockResponseDTO> {
    @Override
    public StockResponseDTO convert(Stock source) {
        return new StockResponseDTO(source);
    }
}
