package com.lawencon.laundry.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_item")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = { "hibernateLazyInitializer" })
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id", nullable = false)
  private ItemDetails itemDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_id", nullable = false)
  private Services services;

  @Column(nullable = false)
  private BigDecimal cost;

  public Item() {
  }

  public Item(Long id, ItemDetails itemDetails, Services services, BigDecimal cost) {
	this.id = id;
	this.itemDetails = itemDetails;
	this.services = services;
	this.cost = cost;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public ItemDetails getItemDetails() {
	return itemDetails;
  }

  public void setItemDetails(ItemDetails itemDetails) {
	this.itemDetails = itemDetails;
  }

  public Services getServices() {
	return services;
  }

  public void setServices(Services services) {
	this.services = services;
  }

  public BigDecimal getCost() {
	return cost;
  }

  public void setCost(BigDecimal cost) {
	this.cost = cost;
  }

}
