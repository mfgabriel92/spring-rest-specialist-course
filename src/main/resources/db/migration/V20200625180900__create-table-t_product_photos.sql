CREATE TABLE t_product_photos (
    product_id int NOT NULL,
    filename varchar(255) NOT NULL,
    description varchar(255) NULL,
    content_type varchar(100) NOT NULL,
    size int NOT NULL,
    PRIMARY KEY (product_id),
    constraint fk_t_product_photos_product_id FOREIGN KEY (product_id) REFERENCES t_products (id)
)