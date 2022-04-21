package com.example.backendassignment.controller;

import com.example.backendassignment.dao.ItemRepository;
import com.example.backendassignment.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/create")
    public void CreateItem(@RequestBody Item item) {
        itemRepository.insert(item);
    }

    @PostMapping("/delete/{id}")
    public void deleteItem(@PathVariable String id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/list")
    public List<Item> listItem() {
        return itemRepository.findAll();
    }

    @PutMapping("/update/{id}")
    public void updateItem(@PathVariable String id, @RequestBody Item student) {
        Optional<Item> s = itemRepository.findById(id);
        Item updated = null;
        if (s.isPresent()) {
            updated = s.get();
            updated.title=student.title;
            updated.description=student.description;
            itemRepository.save(updated);
        } else {
            throw new RuntimeException("values not found for id" + id);
        }
    }
    @GetMapping("list/{id}")
    public Optional<Item> listid(@PathVariable String id){
        return itemRepository.findById(id);
    }
}
