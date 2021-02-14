package com.targetstudy.myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.targetstudy.myretail.business.domain.ProductPrice;
import com.targetstudy.myretail.business.service.ProductPriceService;

@RestController
public class ProductPriceController {

	private ProductPriceService productPriceService;

	private ProductPrice productPrice;

	@Autowired
	public ProductPriceController(ProductPriceService productPriceService) {
		super();
		this.productPriceService = productPriceService;
	}

	@GetMapping("/shopping/products")
	public ProductPrice getProductsWithId(Long id) {
		productPrice = productPriceService.getProductWithpriceFromProductId(id);
		return productPrice;
	}

	@PutMapping("/shopping/products")
	public ProductPrice setProductsWithId(@RequestBody ProductPrice productPrice) {
		productPrice = productPriceService.updateProductWithprice(productPrice.getId(), productPrice);
		return productPrice;
	}

	@PostMapping("/shopping/products")
	public ProductPrice createProducts(@RequestBody ProductPrice productPrice) {
		productPrice = productPriceService.createProductWithprice(productPrice);
		return productPrice;
	}
}
