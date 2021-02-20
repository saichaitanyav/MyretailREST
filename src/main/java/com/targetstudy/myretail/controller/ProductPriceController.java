package com.targetstudy.myretail.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;
import com.targetstudy.myretail.business.domain.ProductPrice;
import com.targetstudy.myretail.business.service.ProductPriceService;
import com.targetstudy.myretail.exception.ProductNotFoundException;

@RestController
public class ProductPriceController {

	private ProductPriceService productPriceService;

	@Autowired
	public ProductPriceController(ProductPriceService productPriceService) {
		super();
		this.productPriceService = productPriceService;
	}

	@GetMapping("/shopping/products")
	public ResponseEntity<List<ProductPrice>> getAllProducts() throws SQLException {
		List<ProductPrice> listProductPrice = productPriceService.getProducts();
		return new ResponseEntity<List<ProductPrice>>(listProductPrice, HttpStatus.OK);
	}

	@GetMapping("/shopping/product")
	public ResponseEntity<ProductPrice> getProductWithId(@NotNull String productId)
			throws SQLException, NoSuchElementException {
		ProductPrice productPrice = productPriceService.getProductWithpriceFromProductId(Long.decode(productId));
		if (null == productPrice) {
			throw new ProductNotFoundException("Product with Id not found");
		}
		return new ResponseEntity<ProductPrice>(productPrice, HttpStatus.OK);
	}

	@PutMapping("/shopping/product")
	public ResponseEntity<ProductPrice> updateProductWithId(@RequestBody ProductPrice productPriceTobeUpdated)
			throws SQLException, ProductNotFoundException, NoSuchElementException {
		ProductPrice productPrice = productPriceService.updateProductWithprice(productPriceTobeUpdated);
		return new ResponseEntity<ProductPrice>(productPrice, HttpStatus.OK);
	}

	@PostMapping("/shopping/product")
	public ResponseEntity<ProductPrice> createProduct(@RequestBody ProductPrice productPrice) throws SQLException {
		productPrice = productPriceService.createProductWithprice(productPrice);
		return new ResponseEntity<ProductPrice>(productPrice, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/shopping/product")
	public ResponseEntity<ProductPrice> deleteProduct(@NotNull String productId) throws SQLException {
		productPriceService.deleteProduct(Long.decode(productId));
		return new ResponseEntity<ProductPrice>(HttpStatus.NO_CONTENT);
	}
}
