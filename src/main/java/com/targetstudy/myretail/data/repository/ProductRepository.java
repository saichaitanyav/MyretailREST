package com.targetstudy.myretail.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.targetstudy.myretail.data.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
