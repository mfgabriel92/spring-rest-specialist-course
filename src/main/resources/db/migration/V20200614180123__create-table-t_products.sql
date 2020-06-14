CREATE TABLE t_products (
    id int NOT NULL AUTO_INCREMENT,
    active bit(1) NOT NULL,
    description varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    price decimal(19,2) NOT NULL,
    restaurant_id int NOT NULL,
    PRIMARY KEY (id),
    KEY FKj6is2plf510gt7euxqmsx528b (restaurant_id),
    CONSTRAINT FKj6is2plf510gt7euxqmsx528b FOREIGN KEY (restaurant_id) REFERENCES t_restaurants (id)
)