package pharma.magazine.adapters.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pharma.magazine.adapters.api.model.ProductDto;
import pharma.magazine.domain.model.ProductModel;

import java.time.format.DateTimeFormatter;

@Component
public class ProductEndpointService implements IDtoConverter<ProductModel, ProductDto>{

    @Autowired
    private StaffEndpointService staffEndpointService;

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public ProductDto converter(ProductModel productModel) {
        ProductDto productDto = new ProductDto();
        productDto.setActive(productModel.getActive());
        productDto.setBrand(productModel.getBrand());
        productDto.setId(productModel.getId());
        productDto.setName(productModel.getName());
        productDto.setPrice(productModel.getPrice());
        productDto.setCreatedAt(productModel.getCreatedAt());
        productDto.setCreatedByDto(staffEndpointService.converter(productModel.getCreatedBy()));
        productDto.setUpdatedByDto(staffEndpointService.converter(productModel.getUpdatedBy()));
        return productDto;
    }
}
