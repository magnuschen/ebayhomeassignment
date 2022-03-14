package com.ebay;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ebay.entity.Item;
import com.ebay.service.ItemOddNumberOperationImpl;
import com.ebay.service.ItemService;

@SpringBootTest
class EbayApplicationTests {

	@Autowired
	private ItemService service;
	
	@Autowired
	private ItemOddNumberOperationImpl operation;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testOddNumberItems(){
		List<Item> items = service.findItemsByOperation(operation);
		for(Item item : items) {
			assertThat(item.getId()%2 !=0).isTrue();
		}
	}
}
