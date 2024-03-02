INSERT INTO STORES(ID,
                   STORENAME,
                   DESCRIPTION,
                   OWNERID)
VALUES ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 'KATANI', 'PESE KHRAB KRLO', '640b238f-a85e-40ce-b754-9dd7607469bc');

INSERT INTO MENUCATEGORIES (ID,
                            STOREID,
                            NAME)
VALUES (1, 'e3ac9673-0629-4df6-89d4-5199f527bfd4', 'Snacks'),
       (2, 'e3ac9673-0629-4df6-89d4-5199f527bfd4', 'Main course'),
       (3, 'e3ac9673-0629-4df6-89d4-5199f527bfd4', 'Beverages');

SELECT setval('menucategories_id_seq', 3);

INSERT INTO ITEMS (StoreID,
                   CATEGORYID,
                   Name,
                   Description,
                   Price)
VALUES ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 1, 'Manchurian', 'dry', 60),
        ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 1, 'Pasta', 'red sauce', 100),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 2, 'Kadahi paneer', ' kadahi paneer', 230),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 2, 'Shahi paneer', ' kadahi paneer', 230),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 2, 'paneer do pyaza', ' kadahi paneer', 230),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 2, 'Dal makhni', ' vdia wali daal', 150),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 1, 'Lassi', 'namkeen', 30),
       ('e3ac9673-0629-4df6-89d4-5199f527bfd4', 1, 'Mango shake', ' large glass', 40);

SELECT setval('items_id_seq', 8)