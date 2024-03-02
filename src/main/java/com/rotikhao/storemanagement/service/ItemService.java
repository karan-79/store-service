package com.rotikhao.storemanagement.service;

import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import com.rotikhao.storemanagement.dao.ItemsDao;
import com.rotikhao.storemanagement.models.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemsDao itemsDao;

    public List<Item> getAllItems(UUID storeId) {
        return itemsDao.getAllItems(storeId);
    }

    public Item createItem(CreateItemRequest req, UUID storeId) {
        var id = itemsDao.createItem(req, storeId);
        return new Item(id, storeId, req);
    }

    public void updateItem(Item item, UUID storeId) {
        itemsDao.updateItem(item, storeId);
    }

    public void delete(int itemId, UUID storeId){
        itemsDao.deleteItem(itemId, storeId);
    }

    public void deleteFromCategory(int categoryId, UUID storeId){
        itemsDao.deleteAllInCategory(categoryId, storeId);
    }

}
