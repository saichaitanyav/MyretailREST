package com.targetstudy.myretail.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetstudy.myretail.business.domain.CurrentPrice;
import com.targetstudy.myretail.business.domain.ProductPrice;
import com.targetstudy.myretail.business.service.ProductPriceService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestMVCProductPriceController.class)
@WebMvcTest(value = TestMVCProductPriceController.class)
public class TestMVCProductPriceController {
	@MockBean
	private ProductPrice mockProductPrice;

	@MockBean
	private ProductPriceController productPriceController;

	@MockBean
	private ProductPriceService mockProductPriceService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getProductDetailsTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping/product")
				.accept(MediaType.APPLICATION_JSON).queryParam("productId", "16752459")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	public void getAllProductsTest() throws Exception {
		Mockito.when(mockProductPriceService.getProducts()).thenReturn(getMockProductList());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping/products")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	public void createProductTest() throws Exception {
		Mockito.when(mockProductPriceService.createProductWithprice(Mockito.any(ProductPrice.class)))
				.thenReturn(getMockProduct());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shopping/product")
				.accept(MediaType.APPLICATION_JSON).content(mapToJson(mapToJson(getMockProduct())))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	public void updateProductTest() throws Exception {
		
		Mockito.when(mockProductPriceService.updateProductWithprice(Mockito.any(ProductPrice.class)))
				.thenReturn(createMockProduct((long) 13860429, "The Big Lebowski Smart TV", (float) (14.3), "EUR"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/shopping/product")
				.accept(MediaType.APPLICATION_JSON).content(mapToJson(getMockProduct())).contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

		assertEquals(200, response.getStatus());

	}

	private ProductPrice getMockProduct() {
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrency_code("USD");
		currentPrice.setValue((float) (17.50));

		ProductPrice mockProduct = new ProductPrice((long) 16752459, "The Big Lebowski (Blu-ray)", currentPrice);
		return mockProduct;
	}

	private List<ProductPrice> getMockProductList() {
		List<ProductPrice> productPriceList = new ArrayList<ProductPrice>();
		productPriceList.add(createMockProduct(13860428, "The Big Lebowski (Blu-ray)", (float) (12.3), "USD"));
		productPriceList.add(createMockProduct(13860429, "The Big Lebowski Smart TV", (float) (14.3), "EUR"));
		return productPriceList;

	}

	private ProductPrice createMockProduct(long id, String name, float currencyValue, String currencyCode) {
		ProductPrice productPrice = new ProductPrice();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrency_code(currencyCode);
		currentPrice.setValue(currencyValue);
		productPrice.setId(id);
		productPrice.setName(name);
		productPrice.setCurrent_price(currentPrice);
		return productPrice;
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

}
