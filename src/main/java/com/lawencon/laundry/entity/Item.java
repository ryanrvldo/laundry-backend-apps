package com.lawencon.laundry.entity;

/**
 * @author Rian Rivaldo
 */
public class Item {

	private Long id;
	private String code;
	private String name;
	private ItemService itemService;

	public Item() {
	}

	public Item(Long id, String code, String name, ItemService itemService) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.itemService = itemService;
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

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
}
