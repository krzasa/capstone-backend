package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // This means that this class is a Controller
@RequestMapping(path="/algo") // This means URL's start with /demo (after Application path)
public class MainController {

    //each sorting method will be given with a Javascrip example
    @Autowired // This means to get the bean called userRepository
    private ItemRepository itemRepository;;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewItem (@RequestBody Item item) {

        itemRepository.save(item);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAllItems() {
        // This returns a JSON or XML with the items
        return itemRepository.findAll();
    }
    @GetMapping(path="/{id}")
    public @ResponseBody Item getItemById(@PathVariable Integer id) {
        // This returns a JSON or XML with the items
        return itemRepository.findById(id).get();
    }
    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateItemById(@PathVariable Integer id, @RequestBody Item item) {
        Item itemToUpdate = itemRepository.findById(id).orElse(null);
        itemToUpdate.setName(item.getName());
        itemToUpdate.setDescription(item.getDescription());
        itemToUpdate.setExample(item.getExample());
        itemRepository.save(itemToUpdate);
        return "Updated";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteItemById(@PathVariable Integer id) {
        itemRepository.deleteById(id);
        return "Deleted";
    }
}
