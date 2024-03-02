package com.rotikhao.storemanagement.api.v1.web;

import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import com.rotikhao.storemanagement.models.Item;
import com.rotikhao.storemanagement.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/{storeId}/items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAll(@PathVariable("storeId") UUID storeId) {
        return itemService.getAllItems(storeId);
    }

    @GetMapping("/search")
    public List<Item> search(@PathVariable("storeId") UUID storeId, @RequestParam("q") String query){
        return itemService.getSearchedItems(storeId, query);
    }

    @PostMapping
    public Item createItem(@PathVariable("storeId") UUID storeId, @RequestBody CreateItemRequest req) {
        return itemService.createItem(req, storeId);
    }

    @PutMapping
    public void updateItem(@PathVariable("storeId") UUID storeId, Item req) {
        itemService.updateItem(req, storeId);
    }

    @DeleteMapping("/{itemId}")

    public void deleteItem(@PathVariable("storeId") UUID storeId,
                           @PathVariable("itemId") int itemId) {
        itemService.delete(itemId, storeId);
    }
}
