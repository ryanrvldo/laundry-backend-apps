package com.lawencon.laundry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rian Rivaldo
 */
@Entity
@Table(name = "tb_m_item_dtl")
@JsonInclude(content = Include.NON_NULL)
public class ItemDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false, unique = true)
  private String code;

  @Column(name = "item_name", nullable = false, unique = true)
  private String name;

  public ItemDetails() {
  }

  public ItemDetails(Long id, String code, String name) {
	this.id = id;
	this.code = code;
	this.name = name;
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

}
