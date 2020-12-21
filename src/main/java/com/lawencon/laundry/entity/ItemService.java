package com.lawencon.laundry.entity;

import java.math.BigDecimal;

/**
 * @author Rian Rivaldo
 */
public class ItemService {

	private Long id;
	private String code;
	private String name;
	private BigDecimal cost;
	private Long hourDuration;

	public ItemService() {
	}

	public ItemService(Long id, String code, String name, BigDecimal cost, Long hourDuration) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.cost = cost;
		this.hourDuration = hourDuration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal price) {
		this.cost = price;
	}

	public Long getHourDuration() {
		return hourDuration;
	}

	public void setHourDuration(Long hourDuration) {
		this.hourDuration = hourDuration;
	}

}
