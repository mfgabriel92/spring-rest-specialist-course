INSERT INTO t_users (id, name, email, password, created_at)
VALUES (1, 'Friedrick Fookes', 'ffookes0@mac.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (2, 'Orson Abdon', 'oabdon1@msn.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (3, 'Cherilyn Giorgio', 'cgiorgio2@umich.edu', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP);

INSERT IGNORE INTO t_states (id, name)
VALUES (1, 'California');

INSERT IGNORE INTO t_cities (id, name, state_id)
VALUES (1, 'City Of Commerce', 1);

INSERT IGNORE INTO t_cuisines (id, name)
VALUES (1, 'American');

INSERT IGNORE INTO t_restaurants (id, name, shipping_fee, cuisine_id, address_zip_code, address_street_name, address_number, address_apartment, address_neighborhood, address_city_id, active, open, created_at, updated_at)
VALUES (1, 'The Brimstone Wok', 5.75, 1, '90040', 'Joy Lane', 1384, null, null, 1, 1, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);

INSERT IGNORE INTO t_payment_methods (id, description)
VALUES (1, 'Credit Card'),
       (2, 'Debit Card'),
       (3, 'Cash');

INSERT IGNORE INTO t_restaurants_payment_methods (restaurant_id, payment_method_id)
VALUES (1, 1),
       (1, 3);

INSERT IGNORE INTO t_products (id, name, description, price, active, restaurant_id)
VALUES (1, 'Bacon ‘n’ Egg Bundles', 'This is a fun way to serve bacon and eggs all in one bite!', 12.75, 1, 1),
       (2, 'Individual Baked Eggs', 'Eggs surrounded by a strip of bacon, and topped with a square of cheese', 13.25, 1, 1);

INSERT IGNORE INTO t_groups (id, name)
VALUES (1, 'Read'), (2, 'Read & Write'), (3, 'Read, Write & Delete');

INSERT IGNORE INTO t_permissions (id, name, description)
VALUES (1, 'READ_RESOURCE', 'Read'),
       (2, 'WRITE_RESOURCE', 'Write'),
       (3, 'DELETE_RESOURCE', 'Delete');

INSERT IGNORE INTO t_groups_permissions (group_id, permission_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3);

INSERT IGNORE INTO t_users_groups (user_id, group_id)
VALUES (1, 1),
       (2, 2),
       (3, 3)
