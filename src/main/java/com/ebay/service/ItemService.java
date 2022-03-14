package com.ebay.service;

import java.util.List;

import com.ebay.entity.Item;

public interface ItemService {
	public Item findItemById(long id);
	public Item findItemByTitle(String title);
	public List<Item> findAllItem();
	public Item saveItem(Item item);
	public List<Item> saveItems(List<Item> items);
	public void deleteItem(Item item);
	public void deleteAllItem(List<Item> items);
	public List<Item> findItemsByOperation(ItemOperation itemOperation);
}
