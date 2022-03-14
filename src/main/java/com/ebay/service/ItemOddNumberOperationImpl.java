package com.ebay.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.data.ItemRepository;
import com.ebay.entity.Item;

@Service
public class ItemOddNumberOperationImpl implements ItemOperation{
	
	@Autowired
	private ItemRepository repository;
	
	@Override
	public List<Item> findItems(){
		return repository.findAll().stream().filter(e->e.getId()%2 != 0).collect(Collectors.toList());
	}
}
