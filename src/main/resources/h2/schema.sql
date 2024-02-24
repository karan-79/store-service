DROP table if exists Stores;

CREATE TABLE STORES (
    ID              UUID DEFAULT random_uuid() primary key ,
    StoreName       VARCHAR(255) NOT NULL,
--     Address         TEXT,
    Description     TEXT,
    Location        VARCHAR(255),
    OwnerID         UUID NOT NULL,
    CreationDate    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastUpdated     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)

