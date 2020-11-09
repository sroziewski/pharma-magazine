package pharma.magazine.adapters.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pharma.magazine.adapters.api.model.ResponsePayload;
import pharma.magazine.adapters.api.model.StockDto;
import pharma.magazine.adapters.api.service.Crud;
import pharma.magazine.adapters.api.service.IDtoConverter;
import pharma.magazine.adapters.api.service.StockEndpointService;
import pharma.magazine.adapters.magazinedb.entity.Stock;
import pharma.magazine.adapters.service.SessionService;
import pharma.magazine.domain.model.StockModel;
import pharma.magazine.domain.ports.service.ICrudService;
import pharma.magazine.domain.ports.service.StockService;

import java.util.function.Function;

@RestController
@RequestMapping("/stocks")
public class StockEndpoint extends Crud<StockDto, StockModel, Stock> {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private StockService stockService;
    @Autowired
    private StockEndpointService stockEndpointService;


    @ApiOperation("List of stocks")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getProducts() {
        return getEntities();
    }

    @ApiOperation("Create a stock given as JSON")
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponsePayload> createStock(@RequestBody StockDto stockDto) {
        Function<StockModel, Stock> function = model -> Stock.of(model);
        return createEntity(StockDto.class, stockDto, function);
    }

    @ApiOperation("Update a stock")
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponsePayload> updateDocument(@RequestBody StockDto stockDto) {
        Function<StockModel, Stock> function = model -> Stock.of(model);
        return updateEntity(StockDto.class, stockDto, function);
    }

    @Override
    protected SessionService getSession() {
        return sessionService;
    }

    @Override
    protected ICrudService<StockModel> getCrudService() {
        return stockService;
    }

    @Override
    protected IDtoConverter<StockModel, StockDto> getDtoConverterService() {
        return stockEndpointService;
    }
}
