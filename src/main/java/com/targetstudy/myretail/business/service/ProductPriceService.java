package com.targetstudy.myretail.business.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.targetstudy.myretail.business.domain.CurrentPrice;
import com.targetstudy.myretail.business.domain.ProductPrice;
import com.targetstudy.myretail.data.entity.Price;
import com.targetstudy.myretail.data.entity.Product;
import com.targetstudy.myretail.data.repository.PriceRepository;
import com.targetstudy.myretail.data.repository.ProductRepository;
import com.targetstudy.myretail.exception.ProductNotFoundException;

@Service
public class ProductPriceService {
	
	@Autowired
	private  ProductRepository productRepository;

	@Autowired
	private  PriceRepository priceRepository;


	public List<ProductPrice> getProducts() throws SQLException {
		List<ProductPrice> listOfProducts = new ArrayList<ProductPrice>();
		Iterable<Product> allProducts = productRepository.findAll();
		allProducts.forEach(product -> {
			Price price = priceRepository.findReservationByProductId(product.getProductId());
			listOfProducts.add(mapProductPriceForeachProduct(product, price));
		});

		return listOfProducts;
	}

	public ProductPrice getProductWithpriceFromProductId(Long productId)
			throws SQLException, ProductNotFoundException, NoSuchElementException {
		ProductPrice productPrice = mapProductPriceForeachProduct(productRepository.findById(productId).get(),
				priceRepository.findReservationByProductId(productId));
		return productPrice;
	}

	public ProductPrice updateProductWithprice(ProductPrice productPriceUpdate)
			throws NullPointerException, ProductNotFoundException, SQLException {
		Optional<Product> product = productRepository.findById(productPriceUpdate.getId());
		product.get().setName(productPriceUpdate.getName());
		productRepository.save(product.get());
		Price price = setPrice(productPriceUpdate);
		priceRepository.save(price);
		return productPriceUpdate;
	}

	private Price setPrice(ProductPrice productPriceUpdate) {
		Price price = priceRepository.findReservationByProductId(productPriceUpdate.getId());
		price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());
		return price;
	}

	public ProductPrice createProductWithprice(ProductPrice productPriceUpdate) throws SQLException {
		Price price = new Price();
		price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());

		Product product = new Product();
		product.setName(productPriceUpdate.getName());
		product.setProductId(productPriceUpdate.getId());

		price.setPriceId(new Random().nextInt(5));
		price.setProductId(productPriceUpdate.getId());

		productRepository.save(product);
		priceRepository.save(price);

		return productPriceUpdate;
	}

	public void deleteProduct(Long deleteProductId) throws SQLException {
		Optional<Product> getProductWithgivenId = productRepository.findById(deleteProductId);

		if (null == getProductWithgivenId.get()) {
			throw new ProductNotFoundException("Delete operation failed.Product Not found");
		}

		productRepository.delete(getProductWithgivenId.get());
		priceRepository.delete(priceRepository.findReservationByProductId(deleteProductId));
	}

	private ProductPrice mapProductPriceForeachProduct(Product product, Price price) {
		ProductPrice productprice = new ProductPrice();
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrency_code(price.getCurrencyCode());
		currentPrice.setValue(price.getPriceValue());
		productprice.setCurrent_price(currentPrice);
		productprice.setName(product.getName());
		productprice.setId(product.getProductId());
		return productprice;
	}

}
