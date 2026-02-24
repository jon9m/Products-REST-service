package com.mmk.rest.restapi;

import com.mmk.rest.restapi.repository.ProductRepository;
import jakarta.validation.Valid;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) - Tomcat initialized with port
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MyTestContainersConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestapiApplicationTests {

    @Autowired
    MockMvc mockMvc;  //Not starting web server - webEnvironment = SpringBootTest.WebEnvironment.MOCK

    @Autowired
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(Matchers.containsString("id")));

        assertThat(productRepository.findAll()).hasSizeGreaterThan(5);
    }


    //FOr debugging

    @Autowired
    DataSource dataSource;

    @Test
    void printConnection() throws Exception {
        System.out.println(dataSource.getConnection().getMetaData().getURL());
    }


    @Value("${external.service.port:-1}")
    int nginxPort;

    @Test
    void printRandomPortProperty() {
        System.out.println(nginxPort);
    }


}
