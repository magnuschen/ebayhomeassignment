package com.ebay.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.ebay.entity.*;
import com.ebay.service.ItemOddNumberOperationImpl;
import com.ebay.service.ItemService;

@RestController
@RequestMapping(path="/api",produces="application/json")
public class ItemApiController {
	private ItemService repo;
	@Autowired
	private ItemOddNumberOperationImpl oddOperation;
	
	public ItemApiController(ItemService repo) {
		this.repo = repo;
	}
	
	@GetMapping(path="/items",produces="application/json")
	public List<Item> getAllItems(){
		return repo.findAllItem();
	}
	
	@PostMapping(path="/items",consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Item> createItems(@RequestBody List<Item> items){
		return repo.saveItems(items);
	}
	
	@PutMapping(path="/items",consumes="application/json")
	public List<Item> updateItems(@RequestBody List<Item> items){
		return repo.saveItems(items);
	}
	
	@DeleteMapping(path="/items",consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItems(@RequestBody List<Item> items) {
		repo.deleteAllItem(items);
	}
	
	@PostMapping(path="/item", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Item createItem(@RequestBody Item item) {
	  return repo.saveItem(item);
	}
	
	@GetMapping(path="/item/{itemId}", produces="application/json")
	public Item getItemById(@PathVariable("itemId") long itemId) {
		return repo.findItemById(itemId);
	}
	
	@GetMapping(path="/item/title/{title}", produces="application/json")
	public Item getItemByTitle(@PathVariable("title") String title) {
		return repo.findItemByTitle(title);
	}
	
	@PutMapping(path="/item", consumes="application/json")
	public Item updateItem(@RequestBody Item item) {
		return repo.saveItem(item);
	}
	
	@DeleteMapping(path="/item", consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@RequestBody Item item) {
		repo.deleteItem(item);
	}
	
	@GetMapping(path="/items/oddnumber", produces="application/json")
	public List<Item> getItemsByOddNumber() {
		return repo.findItemsByOperation(oddOperation);
	}
}
