package com.ebay.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebay.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	public Item findByTitle(String title);
	
	//@Query("select i from item i where mod(column_name,2)=0")
	//public List<Item> itemWithId();
}
