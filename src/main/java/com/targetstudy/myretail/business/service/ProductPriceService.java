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
import com.targetstudy.myretail.exception.ProductNotFoundException;

/**
 * Service which retrieves price information and maps it with product
 * @author saichaitanya
 *
 */
@Service
public class ProductPriceService {

	@Autowired
	private ProductService productService;

	@Autowired
	private PriceService priceService;

	/**
	 * Get All Products With Price information
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<ProductPrice> getAllProductsWithPrice() throws SQLException {
		List<ProductPrice> listOfProducts = new ArrayList<ProductPrice>();

		Iterable<Product> allProducts = productService.getProducts();
		allProducts.forEach(product -> {
			Price price = new Price();
			try {
				price = priceService.getPriceWithProductId(product.getProductId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			listOfProducts.add(mapProductPriceForeachProduct(product, price));
		});

		return listOfProducts;
	}

	/**
	 * Get Product with price information for a given product Id
	 * 
	 * @param productId
	 * @return ProductWithPrice
	 * @throws SQLException
	 * @throws ProductNotFoundException
	 * @throws NoSuchElementException
	 */
	public ProductPrice getProductWithpriceFromProductId(Long productId)
			throws SQLException, ProductNotFoundException, NoSuchElementException {
		return mapProductPriceForeachProduct(productService.getProductWithId(productId).get(),
				priceService.getPriceWithProductId(productId));
	}

	/**
	 * Update Product with price details
	 * 
	 * @param productPriceUpdate
	 * @return ProductWithPrice
	 * @throws NullPointerException
	 * @throws ProductNotFoundException
	 * @throws SQLException
	 */
	public ProductPrice updateProductWithprice(ProductPrice productPriceUpdate)
			throws NullPointerException, ProductNotFoundException, SQLException {
		Product product = productService.getProductWithId(productPriceUpdate.getId()).get();
		product.setName(productPriceUpdate.getName());
		productService.saveProduct(product);
		Price price = setPriceFromRequest(productPriceUpdate);
		priceService.savePrice(price);
		return productPriceUpdate;
	}

	/**
	 * Set Price information of the product
	 * 
	 * @param productPriceUpdate
	 * @return Price
	 * @throws SQLException
	 */
	private Price setPriceFromRequest(ProductPrice productPriceUpdate) throws SQLException {
		Price price = priceService.getPriceWithProductId(productPriceUpdate.getId());
		price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());
		return price;
	}

	/**
	 * Create a product with details from request
	 * 
	 * @param productPriceUpdate
	 * @return productwithPrice
	 * @throws SQLException
	 */
	public ProductPrice createProductWithprice(ProductPrice productPriceUpdate) throws SQLException {

		Price price = new Price();
		price.setPriceValue(productPriceUpdate.getCurrent_price().getValue());
		price.setCurrencyCode(productPriceUpdate.getCurrent_price().getCurrency_code());
		price.setPriceId(new Random().nextInt(5));
		price.setProductId(productPriceUpdate.getId());

		Product product = new Product();
		product.setName(productPriceUpdate.getName());
		product.setProductId(productPriceUpdate.getId());

		productService.saveProduct(product);
		priceService.savePrice(price);

		return productPriceUpdate;
	}

	/**
	 * Delete Product and corresponding price
	 * 
	 * @param deleteProductId
	 * @throws SQLException
	 */
	public void deleteProduct(Long deleteProductId) throws SQLException {
		Optional<Product> getProductWithgivenId = productService.getProductWithId(deleteProductId);

		Product product = getProductWithgivenId.get();
		if (null == product) {
			throw new ProductNotFoundException("Delete operation failed.Product Not found");
		}

		productService.deleteProduct(product);
		priceService.deletePrice(priceService.getPriceWithProductId(deleteProductId));
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
