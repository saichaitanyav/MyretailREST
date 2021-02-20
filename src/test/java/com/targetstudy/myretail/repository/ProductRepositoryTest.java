package com.targetstudy.myretail.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.targetstudy.myretail.data.entity.Product;
import com.targetstudy.myretail.data.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ProductRepository.class)
@WebMvcTest(value = ProductRepository.class)
public class ProductRepositoryTest {
	@MockBean
	private ProductRepository mockRepository;

	@BeforeEach
	public void init() {
		Product productDetail = new Product((long) 13860428, "The Big Lebowski (Blu-ray)");
		when(mockRepository.findById((long) 13860428)).thenReturn(Optional.of(productDetail));
	}

	@Test
	public void findProductByIdFoundTest() throws JSONException {
		Optional<Product> result = mockRepository.findById((long) 13860428);
		assertTrue(result.isPresent());
	}

	@Test
	public void findProductByIdNotFoundTest() throws JSONException {
		Optional<Product> result = mockRepository.findById((long) 138604281);
		assertFalse(result.isPresent());
	}

	@Test
	public void findProductByIdMatchIdTest() throws JSONException {
		Optional<Product> result = mockRepository.findById((long) 13860428);
		assertEquals(13860428, result.get().getProductId());
	}

	@Test
	public void findProductByIdNotMatchIdTest() throws JSONException {
		Optional<Product> result = mockRepository.findById((long) 13860428);
		assertNotEquals(138604281, result.get().getProductId());
	}
}
