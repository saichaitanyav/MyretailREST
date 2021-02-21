package com.targetstudy.myretail.business.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.targetstudy.myretail.data.entity.Product;
import com.targetstudy.myretail.data.repository.ProductRepository;

/**
 * Service which handles the operations on product repository
 * 
 * @author saichaitanya
 *
 */
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Get Products
	 * 
	 * @return Iterable<Product>
	 */
	public Iterable<Product> getProducts() throws SQLException{
		return productRepository.findAll();
	}


	/**
	 * Get Product with Id provided
	 * 
	 * @return Optional<Product>
	 */
	public Optional<Product> getProductWithId(Long productId) throws SQLException {
		return productRepository.findById(productId);
	}

	/**
	 * Create Product 
	 * @param product
	 * @throws SQLException
	 */
	public void saveProduct(Product product) throws SQLException {
		 productRepository.save(product);
	}

	/**
	 * Create Product 
	 * @param product
	 * @throws SQLException
	 */
	public void deleteProduct(Product product) throws SQLException {
		 productRepository.delete(product);
	}
	
}
