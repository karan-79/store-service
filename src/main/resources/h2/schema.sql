DROP table if exists Stores;

CREATE TABLE STORES
(
    ID           UUID      DEFAULT random_uuid() PRIMARY KEY,
    StoreName    VARCHAR(255) NOT NULL,
--     Address         TEXT,
    Description  TEXT,
    Location     VARCHAR(255),
    OwnerID      UUID         NOT NULL,
    CreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastUpdated  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE MENUCATEGORIES
(
    ID      SERIAL       PRIMARY KEY,
    STOREID UUID         NOT NULL,
    NAME    VARCHAR(255) NOT NULL,
    FOREIGN KEY (STOREID) REFERENCES STORES (ID)
);

CREATE TABLE ITEMS
(
    ID           SERIAL         PRIMARY KEY,
    StoreID      UUID           NOT NULL,
    CATEGORYID   INT            NOT NULL,
    Name         VARCHAR(255)   NOT NULL,
    Description  TEXT,
    Price        DECIMAL(10, 2) NOT NULL,
    Availability BOOLEAN        NOT NULL DEFAULT TRUE,
    FOREIGN KEY (STOREID) REFERENCES STORES (ID),
    FOREIGN KEY (CATEGORYID) REFERENCES MENUCATEGORIES (ID)
);
