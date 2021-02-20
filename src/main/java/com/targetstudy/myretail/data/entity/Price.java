package com.targetstudy.myretail.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;



@Entity
@Table(name = "Price")
public class Price {

	@Id
	@Column(name = "PRICE_ID")
	private long priceId;

	@NotNull
	@Column(name = "PRODUCT_ID")
	private long productId;

	@Column(name = "PRICE_VALUE")
	private float priceValue;

	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Price(long priceId, long productId, float priceValue, String currencyCode) {
		super();
		this.priceId = priceId;
		this.productId = productId;
		this.priceValue = priceValue;
		this.currencyCode = currencyCode;
	}

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
