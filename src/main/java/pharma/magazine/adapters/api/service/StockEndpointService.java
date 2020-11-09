package pharma.magazine.adapters.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pharma.magazine.adapters.api.model.StockDto;
import pharma.magazine.domain.model.StockModel;

@Component
public class StockEndpointService implements IDtoConverter<StockModel, StockDto>{

    @Autowired
    private StaffEndpointService staffEndpointService;

    @Override
    public StockDto converter(StockModel stockModel) {
        StockDto stockDto = new StockDto();
        stockDto.setProductId(stockModel.getProductId());
        stockDto.setQuantity(stockModel.getQuantity());
        stockDto.setStoreId(stockModel.getStoreId());
        stockDto.setCreatedAt(stockModel.getCreatedAt());
        stockDto.setCreatedByDto(staffEndpointService.converter(stockModel.getCreatedBy()));
        stockDto.setUpdatedByDto(staffEndpointService.converter(stockModel.getUpdatedBy()));
        return stockDto;
    }
}
