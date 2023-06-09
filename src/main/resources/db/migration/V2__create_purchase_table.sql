CREATE TABLE IF NOT EXISTS PURCHASE
(
    ID      BIGSERIAL PRIMARY KEY,
    ITEM    VARCHAR(255)     NOT NULL,
    AMOUNT  DOUBLE PRECISION NOT NULL,
    DATE    DATE             NOT NULL,
    USER_ID BIGINT           NOT NULL,
    CONSTRAINT fk_purchase_user FOREIGN KEY (USER_ID) REFERENCES REWARD_USER (ID)
);

CREATE SEQUENCE IF NOT EXISTS purchase_id_seq;
ALTER SEQUENCE IF EXISTS purchase_id_seq RESTART WITH 100;
