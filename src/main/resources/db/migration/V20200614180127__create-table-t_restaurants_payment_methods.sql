CREATE TABLE t_restaurants_payment_methods (
    restaurant_id int NOT NULL,
    payment_method_id int NOT NULL,
    PRIMARY KEY (restaurant_id, payment_method_id),
    KEY FKasx6vp7ra2uc712nmv944x86p (payment_method_id),
    KEY FKeocppyeujy1a1r4ror2uase7h (restaurant_id),
    CONSTRAINT FKasx6vp7ra2uc712nmv944x86p FOREIGN KEY (payment_method_id) REFERENCES t_payment_methods (id),
    CONSTRAINT FKeocppyeujy1a1r4ror2uase7h FOREIGN KEY (restaurant_id) REFERENCES t_restaurants (id)
)