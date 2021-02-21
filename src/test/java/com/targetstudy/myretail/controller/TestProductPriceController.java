package com.targetstudy.myretail.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.targetstudy.myretail.business.domain.CurrentPrice;
import com.targetstudy.myretail.business.domain.ProductPrice;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestProductPriceController.class)
@WebMvcTest(value = TestProductPriceController.class)
public class TestProductPriceController {
	@MockBean
	private ProductPrice mockProductPrice;

	@MockBean
	private ProductPriceController productPriceController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	List<ProductPrice> listProductPrice = new ArrayList<ProductPrice>();

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@BeforeEach
	public void init() throws Exception {
		List<ProductPrice> listProductPrice = initializeProductContent();

		when(productPriceController.getAllProducts())
				.thenReturn(new ResponseEntity<List<ProductPrice>>(listProductPrice, HttpStatus.OK));
		ProductPrice productPrice = createMockProduct((long) 13860428, "The Big Lebowski (Blu-ray)", (float) (12.3),
				"USD");
		when(productPriceController.getProductWithId("13860428"))
				.thenReturn(new ResponseEntity<ProductPrice>(productPrice, HttpStatus.OK));
	}

	@Test
	public void testProductFound() throws Exception {
		ResponseEntity<ProductPrice> priceProduct = productPriceController.getProductWithId("13860428");
		assertNotNull(priceProduct);
		assertEquals(HttpStatus.OK, priceProduct.getStatusCode());
	}

	@Test
	public void testProductNotFound() throws Exception {
		ResponseEntity<ProductPrice> priceProduct = productPriceController.getProductWithId("13890428");
		assertNull(priceProduct);
	}

	@Test
	public void getProductAllDetailsTest() throws Exception {
		ResponseEntity<List<ProductPrice>> priceProducts = productPriceController.getAllProducts();
		assertNotNull(priceProducts);
		assertEquals(HttpStatus.OK, priceProducts.getStatusCode());
	}

	@Test
	public void updateProductTest() throws Exception {

		ProductPrice priceOfProduct = createMockProduct(13860429, "The Big Lebowski Smart TV", (float) (14.3), "EUR");

		when(productPriceController.updateProductWithId(priceOfProduct))
				.thenReturn(new ResponseEntity<ProductPrice>(priceOfProduct, HttpStatus.OK));

		ResponseEntity<ProductPrice> priceProductUpdated = productPriceController.updateProductWithId(priceOfProduct);

		assertNotNull(priceProductUpdated);
		assertEquals(HttpStatus.OK, priceProductUpdated.getStatusCode());

	}

	@Test
	public void createProductTest() throws Exception {
		ProductPrice priceOfProduct = createMockProduct(13860428, "The Big Lebowski Smart TV", (float) (12.3), "EUR");

		when(productPriceController.createProduct(priceOfProduct))
				.thenReturn(new ResponseEntity<ProductPrice>(priceOfProduct, HttpStatus.CREATED));

		ResponseEntity<ProductPrice> priceProductcreated = productPriceController.createProduct(priceOfProduct);

		assertNotNull(priceProductcreated);
		
		assertEquals(HttpStatus.CREATED, priceProductcreated.getStatusCode());

	}

	private List<ProductPrice> initializeProductContent() {
		List<ProductPrice> productPriceList = new ArrayList<ProductPrice>();
		ProductPrice productOnePrice = createMockProduct(13860428, "The Big Lebowski (Blu-ray)", (float) (12.3), "USD");
		ProductPrice productTwoPrice = createMockProduct(13860429, "The Big Lebowski Smart TV", (float) (14.3), "EUR");
		productPriceList.add(productOnePrice);
		productPriceList.add(productTwoPrice);
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
}
