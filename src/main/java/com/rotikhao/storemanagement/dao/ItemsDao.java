package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import com.rotikhao.storemanagement.models.Item;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ItemsDao {

    private JdbcClient jdbcClient;

    public List<Item> getAllItems(String storeId) {
        return jdbcClient.sql("SELECT * FROM ITEMS WHERE STOREID = :storeId")
                .param("storeId", storeId)
                .query(Item.class)
                .list();
    }

    public int createItem(CreateItemRequest createItemRequest, String storeId) {
        var sql = """
                INSERT INTO ITEMS (STOREID, CATEGORYID, NAME, DESCRIPTION, PRICE)
                VALUES (:storeId, :categoryId, :name, :description, :price)
                RETURNING ID
                """;
        return jdbcClient.sql(sql)
                .param("storeId",storeId)
                .param("categoryId", createItemRequest.categoryId())
                .param("name", createItemRequest.name())
                .param("description", createItemRequest.description())
                .param("price", createItemRequest.price())
                .query(Integer.class)
                .single();
    }

    public void updateItem(Item item, String storeId) {
        var sql = """
                UPDATE ITEMS SET CATEGORYID = :cid, Name = :name, Description = :des,
                        Price = :price, Availability = :availability
                        WHERE STOREID = :storeId AND ID = :id
                """;
        jdbcClient.sql(sql)
                .param("cid", item.getCategoryId())
                .param("name", item.getName())
                .param("des", item.getDescription())
                .param("price", item.getPrice().doubleValue())
                .param("availability", item.isAvailability())
                .param("storeId", storeId)
                .param("id", item.getId())
                .update();
    }
}