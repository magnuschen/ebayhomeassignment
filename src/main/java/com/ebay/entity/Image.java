package com.ebay.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Image{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	private String url;
	@ManyToOne
	private Item item;
	
	public Image() {}
	public Image(String url, Item item) {
		super();
		this.url = url;
		this.item = item;
	}
	
	public void setId(long Id) {
		this.Id = Id;
	}
	
	public long getId() {
		return Id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Item getItem() {
		return item;
	}
	@JsonBackReference
	public void setItem(Item item) {
		this.item = item;
	}
}
