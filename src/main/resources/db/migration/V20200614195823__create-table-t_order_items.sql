CREATE TABLE t_order_items (
    id int NOT NULL AUTO_INCREMENT,
    quantity int NOT NULL,
    unit_price decimal(10,2) NOT NULL,
    total_price decimal(10,2) NOT NULL,
    observation varchar(255) DEFAULT NULL,
    order_id int NOT NULL,
    product_id int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY order_id (order_id,product_id),
    KEY t_order_items_ibfk_2 (product_id),
    CONSTRAINT t_order_items_ibfk_1 FOREIGN KEY (order_id) REFERENCES t_orders (id),
    CONSTRAINT t_order_items_ibfk_2 FOREIGN KEY (product_id) REFERENCES t_products (id)
)