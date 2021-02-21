package com.targetstudy.myretail.business.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.targetstudy.myretail.data.entity.Price;
import com.targetstudy.myretail.data.repository.PriceRepository;

/**
 * Service which handles the operations on price repository
 * 
 * @author saichaitanya
 *
 */
@Service
public class PriceService {
	@Autowired
	private PriceRepository priceRepository;

	/**
	 * Get Price of the Product
	 * 
	 * @param productId
	 * @return
	 */
	public Price getPriceWithProductId(Long productId) throws SQLException {
		return priceRepository.findPriceByProductId(productId);
	}

	/**
	 * Create Price
	 * 
	 * @param price
	 */
	public void savePrice(Price price) throws SQLException {
		priceRepository.save(price);
	}
	
	/**
	 * Create Product 
	 * @param product
	 * @throws SQLException
	 */
	public void deletePrice(Price price) throws SQLException {
		 priceRepository.delete(price);
	}
}
