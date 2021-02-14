package com.targetstudy.myretail.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.targetstudy.myretail.business.domain.CurrentPrice;
import com.targetstudy.myretail.business.domain.ProductPrice;
import com.targetstudy.myretail.data.entity.Price;
import com.targetstudy.myretail.data.entity.Product;
import com.targetstudy.myretail.data.repository.PriceRepository;
import com.targetstudy.myretail.data.repository.ProductRepository;

@Service
public class ProductPriceService {

	private final ProductRepository productRepository;

	private final PriceRepository priceRepository;

	@Autowired
	public ProductPriceService(ProductRepository productRepository, PriceRepository priceRepository) {
		super();
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}

	public ProductPrice getProductWithpriceFromProductId(Long productId) {
		ProductPrice productPrice = new ProductPrice();
		Optional<Product> product = productRepository.findById(productId);
		Price price = priceRepository.findReservationByProductId(productId);
		CurrentPrice priceOfProduct = new CurrentPrice();
		priceOfProduct.setValue(price.getPriceValue());
		priceOfProduct.setCurrency_code(price.getCurrencyCode());
		productPrice.setCurrent_price(priceOfProduct);
		productPrice.setName(product.get().getName());
		productPrice.setId(product.get().getProductId());
		return productPrice;
	}

	public ProductPrice updateProductWithprice(Long productId, ProductPrice productPriceUpdate) {
		Optional<Product> product = productRepository.findById(productId);
		Price price = priceRepository.findReservationByProductId(productId);

		price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());
		// product.get().setName(productPrice.getName());

		// productRepository.saveAll();
		priceRepository.save(price);
		CurrentPrice priceOfProduct = new CurrentPrice();
		priceOfProduct.setValue(price.getPriceValue());
		priceOfProduct.setCurrency_code(price.getCurrencyCode());
		productPriceUpdate.setCurrent_price(priceOfProduct);
		productPriceUpdate.setName(product.get().getName());
		productPriceUpdate.setId(product.get().getProductId());

		return productPriceUpdate;
	}
	
	public ProductPrice createProductWithprice(ProductPrice productPriceUpdate) {
		Price price = new Price();
    	price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());
		
		Product product = new Product();
		product.setName(productPriceUpdate.getName());
		product.setProductId(productPriceUpdate.getId());
		
		price.setPriceId(10);
		price.setProductId(productPriceUpdate.getId());
		
		productRepository.save(product);
		priceRepository.save(price);
		
		return productPriceUpdate;
	}

}
