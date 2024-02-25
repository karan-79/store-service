
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE STORES
(
    ID           UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    StoreName    VARCHAR(255) NOT NULL,
--     Address         TEXT,
    Description  TEXT,
    Location     VARCHAR(255),
    OwnerID      UUID NOT NULL,
    CreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastUpdated  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- If you haven't already, you need to install the uuid-ossp extension to generate UUIDs.