package pharma.magazine.adapters.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pharma.magazine.adapters.api.model.ProductDto;
import pharma.magazine.adapters.api.model.ResponsePayload;
import pharma.magazine.adapters.api.service.Crud;
import pharma.magazine.adapters.api.service.IDtoConverter;
import pharma.magazine.adapters.api.service.ProductEndpointService;
import pharma.magazine.adapters.magazinedb.entity.Product;
import pharma.magazine.adapters.service.SessionService;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.ports.service.ICrudService;
import pharma.magazine.domain.ports.service.ProductService;

import java.util.function.Function;

@RestController
@RequestMapping("/products")
public class ProductEndpoint extends Crud<ProductDto, ProductModel, Product> {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductEndpointService productEndpointService;

    @ApiOperation("List of products")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getProducts() {
        return getEntities();
    }

    @ApiOperation("Create a product given as JSON")
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponsePayload> createProduct(@RequestBody ProductDto productDto) {
        Function<ProductModel, Product> function = model -> Product.of(model);
        return createEntity(ProductDto.class, productDto, function);
    }

    @ApiOperation("Update a product")
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponsePayload> updateDocument(@RequestBody ProductDto productDto) {
        Function<ProductModel, Product> function = model -> Product.of(model);
        return updateEntity(ProductDto.class, productDto, function);
    }

    @Override
    protected SessionService getSession() {
        return sessionService;
    }

    @Override
    protected ICrudService<ProductModel> getCrudService() {
        return productService;
    }

    @Override
    protected IDtoConverter<ProductModel, ProductDto> getDtoConverterService() {
        return productEndpointService;
    }
}
