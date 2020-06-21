CREATE TABLE t_restaurants (
    id int NOT NULL AUTO_INCREMENT,
    address_apartment varchar(255) NULL,
    address_neighborhood varchar(255) NULL,
    address_number varchar(255) NULL,
    address_street_name varchar(255) NULL,
    address_zip_code varchar(255) NULL,
    created_at datetime NOT NULL,
    name varchar(255) NOT NULL,
    shipping_fee decimal(19,2) NOT NULL,
    updated_at datetime NOT NULL,
    address_city_id int  NULL,
    cuisine_id int NOT NULL,
    PRIMARY KEY (id),
    KEY FKimxpj2cx4ici06dfcwja4340q (address_city_id),
    KEY FK7pgr366b6tq7mrwgcncmo2el0 (cuisine_id),
    CONSTRAINT FK7pgr366b6tq7mrwgcncmo2el0 FOREIGN KEY (cuisine_id) REFERENCES t_cuisines (id),
    CONSTRAINT FKimxpj2cx4ici06dfcwja4340q FOREIGN KEY (address_city_id) REFERENCES t_cities (id)
)