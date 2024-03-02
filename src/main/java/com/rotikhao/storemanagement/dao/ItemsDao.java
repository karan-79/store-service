package com.rotikhao.storemanagement.dao;

import com.rotikhao.storemanagement.api.v1.web.models.CreateItemRequest;
import com.rotikhao.storemanagement.models.Item;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ItemsDao {

    private JdbcClient jdbcClient;

    public List<Item> getAllItems(UUID storeId) {
        return jdbcClient.sql("SELECT * FROM ITEMS WHERE STOREID = :storeId")
                .param("storeId", storeId)
                .query(Item.class)
                .list();
    }

    public List<Item> getItemsByText(UUID storeId, String text) {
        var sql = "SELECT * FROM ITEMS WHERE STOREID = "+ "'" + storeId.toString() + "'" + " AND NAME LIKE '%" + text + "%'";
        return jdbcClient.sql(sql)
                .param("storeId", storeId)
                .param("text", text)
                .query(Item.class)
                .list();
    }

    public int createItem(CreateItemRequest createItemRequest, UUID storeId) {
        var sql = """
                INSERT INTO ITEMS (STOREID, CATEGORYID, NAME, DESCRIPTION, PRICE)
                VALUES (:storeId, :categoryId, :name, :description, :price)
                RETURNING ID
                """;
        return jdbcClient.sql(sql)
                .param("storeId", storeId)
                .param("categoryId", createItemRequest.getCategoryId())
                .param("name", createItemRequest.getName())
                .param("description", createItemRequest.getDescription())
                .param("price", createItemRequest.getPrice())
                .query(Integer.class)
                .single();
    }

    public void updateItem(Item item, UUID storeId) {
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

    public void deleteItem(int itemId, UUID storeId) {
        var sql = """
                DELETE FROM ITEMS WHERE ID = :id AND STOREID = :storeId
                """;
        jdbcClient.sql(sql)
                .param("id", itemId)
                .param("storeId", storeId)
                .update();
    }

    public void deleteAllInCategory(int categoryId, UUID storeId) {
        var sql = """
                DELETE FROM ITEMS WHERE CATEGORYID = :cid AND STOREID = :storeId
                """;
        jdbcClient.sql(sql)
                .param("cid", categoryId)
                .param("storeId", storeId)
                .update();
    }
}