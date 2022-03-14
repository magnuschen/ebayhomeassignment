package com.ebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebay.data.ItemRepository;
import com.ebay.entity.Item;

@Repository
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemRepository repository;
	
	@Override
	public Item findItemById(long id) {
	  return repository.getById(id);
	}
	
	@Override
	public Item findItemByTitle(String title) {
		return repository.findByTitle(title);
	}
	
	@Override
	public List<Item> findAllItem() {
		return repository.findAll();
	}
	
	@Override
	@Transactional
	public Item saveItem(Item item) {
	  return repository.save(item);
	}
	
	@Override
	@Transactional
	public List<Item> saveItems(List<Item> items) {
	  return repository.saveAll(items);
	}
	
	@Override
	@Transactional
	public void deleteItem(Item item) {
	  repository.delete(item);
	}
	
	@Override
	@Transactional
	public void deleteAllItem(List<Item> items) {
	  repository.deleteAll(items);
	}
	
	@Override
	public List<Item> findItemsByOperation(ItemOperation itemOperation) {
		return itemOperation.findItems();
	}
}
