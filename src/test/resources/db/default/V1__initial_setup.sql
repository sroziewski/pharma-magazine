-- ID SEQUENCES

CREATE SEQUENCE staff_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE stores_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE products_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




-- MAIN TABLES

CREATE TABLE stores (
                        id bigint NOT NULL DEFAULT nextval('stores_sequence'),
                        name character varying(255),
                        email character varying(255),
                        phone character varying(255),
                        street character varying(255),
                        city character varying(255),
                        zip_code character varying(15),
                        state character varying(255)
);

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);

CREATE UNIQUE INDEX idx_stores_unique_name
    ON stores (name);

CREATE UNIQUE INDEX idx_stores_unique_email
    ON stores (email);

CREATE TABLE staff (
    id bigint NOT NULL DEFAULT nextval('staff_sequence'),
    first_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    password character varying(255),
    phone character varying(255),
    store_id bigint,
    active boolean
);

ALTER TABLE ONLY staff
    ADD CONSTRAINT staff_pkey PRIMARY KEY (id);

CREATE UNIQUE INDEX idx_staff_unique_name
    ON staff (email);

ALTER TABLE ONLY staff
    ADD CONSTRAINT fk_store_has_staff FOREIGN KEY (store_id) REFERENCES stores(id);

CREATE TABLE products (
    id bigint NOT NULL DEFAULT nextval('products_sequence'),
    name character varying(255),
    price DECIMAL,
    brand character varying(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    created_by_id bigint,
    updated_by_id bigint,
    active boolean
);

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);

CREATE UNIQUE INDEX idx_products_unique_name
    ON products (name);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_staff_product_createdby FOREIGN KEY (created_by_id) REFERENCES staff(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_staff_product_updatedby FOREIGN KEY (updated_by_id) REFERENCES staff(id);

CREATE TABLE stocks (
    product_id bigint,
    store_id bigint,
    quantity bigint DEFAULT 0,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    created_by_id bigint,
    updated_by_id bigint
);

ALTER TABLE ONLY stocks
    ADD CONSTRAINT fk_stocks_product_createdby FOREIGN KEY (created_by_id) REFERENCES staff(id);

ALTER TABLE ONLY stocks
    ADD CONSTRAINT fk_stocks_product_updatedby FOREIGN KEY (updated_by_id) REFERENCES staff(id);

ALTER TABLE ONLY stocks
    ADD CONSTRAINT stocks_pkey PRIMARY KEY (product_id, store_id);

ALTER TABLE ONLY stocks
    ADD CONSTRAINT fk_stock_has_product FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY stocks
    ADD CONSTRAINT fk_stock_has_store FOREIGN KEY (store_id) REFERENCES stores(id);



