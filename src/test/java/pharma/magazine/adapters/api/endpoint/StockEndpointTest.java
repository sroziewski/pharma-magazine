package pharma.magazine.adapters.api.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pharma.magazine.adapters.api.model.StockDto;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.model.StaffModel;
import pharma.magazine.domain.model.StockModel;
import pharma.magazine.domain.ports.service.ProductService;
import pharma.magazine.domain.ports.service.StaffService;
import pharma.magazine.domain.ports.service.StockService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@EnableJpaRepositories("pharma.magazine")
@AutoConfigureMockMvc
public class StockEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StaffService staffService;

    private String accessToken = "am9lQGJpZGVuLnVzOnBhc3N3b3Jk";

    @Test
    public void createStock() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        mockMvc.perform(get("/stocks"))
                .andExpect(status().isUnauthorized());

        Optional<StaffModel> byId1 = staffService.findById(1L);
        assert byId1.isPresent();
        StaffModel currentUser = byId1.get();
        ProductModel productModel = productService.create(ProductModel.builder().brand("Brand").name("Name").active(true).build(), currentUser);
        long quantity = 21L;
        StockModel stockModel = StockModel.builder().productId(productModel.getId()).storeId(1L).quantity(quantity).build();
        StockDto stockDto = StockDto.of(stockModel);

        String response = mockMvc.perform(post("/stocks")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + accessToken)
                .content(objectMapper.writeValueAsString(stockDto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String stockId = String.valueOf(new JSONObject(response).get("id"));
        Long storeId = Long.parseLong(String.valueOf(stockId.charAt(1)));
        Long productId = Long.parseLong(String.valueOf(stockId.charAt(4)));

        Optional<StockModel> byId = stockService.findByIds(storeId, productId);
        assert byId.isPresent();
        assert quantity == byId.get().getQuantity();
    }

}
