package com.targetstudy.myretail.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.targetstudy.myretail.data.entity.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
	Price findReservationByProductId(Long productId);
}
