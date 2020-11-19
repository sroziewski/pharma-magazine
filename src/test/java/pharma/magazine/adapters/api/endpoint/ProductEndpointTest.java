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
import pharma.magazine.adapters.api.model.ProductDto;
import pharma.magazine.domain.model.ProductModel;
import pharma.magazine.domain.ports.service.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@EnableJpaRepositories("pharma.magazine")
@AutoConfigureMockMvc
public class ProductEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    private String accessToken = "am9lQGJpZGVuLnVzOnBhc3N3b3Jk";

    @Test
    public void createProduct() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        mockMvc.perform(get("/products"))
                .andExpect(status().isUnauthorized());

        String productName = "Name%^%#";
        ProductModel productModel = ProductModel.builder().active(true).brand("Brand").name(productName).build();
        ProductDto productDto = ProductDto.of(productModel);

        String response = mockMvc.perform(post("/products")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + accessToken)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long productId = Long.parseLong(String.valueOf(new JSONObject(response).get("id")));
        Optional<ProductModel> byId = productService.findById(productId);
        assert byId.isPresent();
        assertEquals(productName, byId.get().getName());
    }

}
