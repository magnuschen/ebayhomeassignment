package com.ebay;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

import com.ebay.data.ImageRepository;
import com.ebay.data.ItemRepository;
import com.ebay.entity.Image;
import com.ebay.entity.Item;
import com.ebay.entity.Item.Condition;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class ItemRepositoryTests {     
	 @Autowired
	 private ItemRepository itemRepository;
	 
	 @Test
	 @Order(1)
	 public void testCreateItem() {
		 Item item = new Item("microservice book",Condition.GOOD,100,1,"learn","microservice in action");
		 Image image1 = new Image("http://www.ebay.book.microservice1.jpg",item);
		 Image image2 = new Image("http://www.ebay.book.microservice2.jpg",item);
		 item.addImageURL(image1);
		 item.addImageURL(image2);
		 itemRepository.save(item);
		 assertThat(item.getId()).isGreaterThan(0);
		 assertThat(item.getImageURL().size()).isEqualTo(2);
		 assertThat(item.getImageURL().get(0).getId()).isGreaterThan(0);
		 assertThat(item.getImageURL().get(1).getId()).isGreaterThan(0);
	 }
	 
	 @Test
	 @Order(2)
	 public void testCreateItems() {
		 List<Item> items = new ArrayList<>();
		 Item item1 = new Item("microservice book",Condition.GOOD,100,1,"learn","microservice in action");
		 Image image1 = new Image("http://www.ebay.book.microservice1.jpg",item1);
		 Image image2 = new Image("http://www.ebay.book.microservice2.jpg",item1);
		 item1.addImageURL(image1);
		 item1.addImageURL(image2);
		 Item item2 = new Item("cloud book",Condition.NEW,100,1,"learn","cloud in action");
		 Image image3 = new Image("http://www.ebay.book.cloud1.jpg",item2);
		 Image image4 = new Image("http://www.ebay.book.cloud2.jpg",item2);
		 item2.addImageURL(image3);
		 item2.addImageURL(image4);
		 items.add(item1);
		 items.add(item2);
		 itemRepository.saveAll(items);
		 assertThat(item1.getId()).isGreaterThan(0);
		 assertThat(item2.getId()).isGreaterThan(0);
		 assertThat(item1.getImageURL().size()).isEqualTo(2);
		 assertThat(item1.getImageURL().get(0).getId()).isGreaterThan(0);
		 assertThat(item1.getImageURL().get(1).getId()).isGreaterThan(0);
	 }
	 
	 @Test
	 @Order(3)
	 public void testFindById() {
		 Item item = new Item("microservice book",Condition.GOOD,100,1,"learn","microservice in action");
		 Image image = new Image("http://www.ebay.book.microservice1.jpg",item);
		 item.addImageURL(image);
		 itemRepository.save(item);
		 Item found = itemRepository.getById(item.getId());
		 assertThat(found.getTitle().equals("microservice book"));
		 Image foundImage = found.getImageURL().get(0);
		 assertThat(foundImage.getUrl().equals("http://www.ebay.book.microservice1.jpg"));
	 }
	 
	 @Test
	 @Order(4)
	 public void testFindByTitle() {
		 Item found = itemRepository.findByTitle("java book");
		 assertThat(found.getTitle().equals("java book"));
	 }
	 
	 @Test
	 @Order(5)
	 public void testFindAllItem() {
		 List<Item> items = (List<Item>) itemRepository.findAll();
		 assertThat(items).size().isGreaterThan(0);
		 for(Item item : items) {
			 assertThat(item.getImageURL().size()).isGreaterThan(0);
		 }
	 }
	 
	 @Test
	 @Order(6)
	 public void testUpdateItem() {
		 Item item = itemRepository.findByTitle("java book");
		 item.setQuantity(100);
		 Image image = item.getImageURL().get(0);
		 image.setUrl("http://www.ebay.book.java3.jpg");
		 itemRepository.save(item);
		 Item found = itemRepository.findByTitle("java book");
		 assertThat(found.getQuantity()).isEqualTo(100);
		 assertThat(found.getImageURL().get(0).getUrl()).isEqualTo("http://www.ebay.book.java3.jpg");
	 }
	 
	 @Test
	 @Order(7)
	 public void testUpdateItems() {
		 List<Item> items = new ArrayList<>();
		 Item item1 = itemRepository.findByTitle("java book");
		 item1.setQuantity(100);
		 Item item2 = itemRepository.findByTitle("spring book");
		 item2.setQuantity(200);
		 items.add(item1);
		 items.add(item2);
		 itemRepository.saveAll(items);
		 Item found1 = itemRepository.findByTitle("java book");
		 assertThat(found1.getQuantity()).isEqualTo(100);
		 Item found2 = itemRepository.findByTitle("spring book");
		 assertThat(found2.getQuantity()).isEqualTo(200);
	 }
	 
	 @Test
	 @Order(8)
	 public void testDeletItem() {
		 long id = 10001;
		 itemRepository.deleteById(id);
		 Item found = itemRepository.findByTitle("java book");
		 assertThat(found).isNull();
	 }
	 
	 @Test
	 @Order(9)
	 public void testDeletItems() {
		 List<Item> items = new ArrayList<>();
		 Item item1 = itemRepository.findByTitle("java book");
		 Item item2 = itemRepository.findByTitle("spring book");
		 items.add(item1);
		 items.add(item2);
		 itemRepository.deleteAll(items);
		 Item found1 = itemRepository.findByTitle("java book");
		 assertThat(found1).isNull(); 
		 Item found2 = itemRepository.findByTitle("spring book");
		 assertThat(found2).isNull(); 
	 }
}
