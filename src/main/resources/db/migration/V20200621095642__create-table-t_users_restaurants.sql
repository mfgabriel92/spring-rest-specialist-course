CREATE TABLE t_restaurants_users (
    restaurant_id int NOT NULL,
    user_id int NOT NULL,
    PRIMARY KEY (user_id, restaurant_id),
    KEY k_t_restaurants_users_user_id (restaurant_id),
    KEY k_t_restaurants_users_restaurant_id (user_id),
    CONSTRAINT fk_t_restaurants_users_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES t_restaurants (id),
    CONSTRAINT fk_t_restaurants_users_user_id FOREIGN KEY (user_id) REFERENCES t_users (id)
)