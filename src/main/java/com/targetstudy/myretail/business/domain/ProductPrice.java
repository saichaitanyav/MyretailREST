package com.targetstudy.myretail.business.domain;

/**
 * DAO bean for product with price 
 * @author saichaitanya
 *
 */
public class ProductPrice {

	public ProductPrice(Long id, String name, CurrentPrice current_price) {
		super();
		Id = id;
		Name = name;
		this.current_price = current_price;
	}

	public ProductPrice() {
		super();
	}

	private Long Id;
	private String Name;
	private CurrentPrice current_price;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public CurrentPrice getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
