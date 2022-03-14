package com.ebay.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String title;
	private Condition condition;
	private double price;
	private int quantity;
	@OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy="item", fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Image> imageURL = new ArrayList<>();
	private String itemSpecifics;
	private String description;
	
	public static enum Condition {
	    NEW,GOOD,POOR
	}
	
	public Item() {}
	
	public Item(String title, Condition condition, double price, int quantity, String itemSpecifics,
			String description) {
		super();
		this.title = title;
		this.condition = condition;
		this.price = price;
		this.quantity = quantity;
		this.itemSpecifics = itemSpecifics;
		this.description = description;
	}
	
	public void setId(long Id) {
		this.Id = Id;
	}
	
	public long getId() {
		return Id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Image> getImageURL() {
		return imageURL;
	}
	
	public Item addImageURL(Image image) {
		image.setItem(this);
		imageURL.add(image);
		return this;
	}

	public Item removeImageURL(Image image) {
		imageURL.remove(image);
		image.setItem(null);
		return this;
	}

	public String getItemSpecifics() {
		return itemSpecifics;
	}

	public void setItemSpecifics(String itemSpecifics) {
		this.itemSpecifics = itemSpecifics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@PrePersist
	private void prePersist() {
		imageURL.forEach( c -> c.setItem(this));
	}
}
