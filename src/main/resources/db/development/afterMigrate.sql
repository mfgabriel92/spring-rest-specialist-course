INSERT INTO t_users (id, name, email, password, created_at)
VALUES (1, 'John Doe', 'johndoe@gmail.com', '123123123', UTC_TIMESTAMP);

INSERT IGNORE INTO t_states (id, name)
VALUES (1, 'Rio Grande do Sul'), (2, 'Santa Catarina'), (3, 'São Paulo'), (4, 'New York'), (5, 'Honshu');

INSERT IGNORE INTO t_cities (id, name, state_id) 
VALUES (1, 'Novo Hamburgo', 1), (2, 'Florianópolis', 2), (3, 'São Paulo', 3), (4, 'Maryland', 4), (5, 'Iwate', 5);

INSERT IGNORE INTO t_cuisines (id, name)
VALUES (1, 'American'), (2, 'Mexican'), (3, 'Japanese');

INSERT IGNORE INTO t_restaurants (id, name, shipping_fee, cuisine_id, address_zip_code, address_street_name, address_number, address_apartment, address_neighborhood, address_city_id, active, created_at, updated_at)
VALUES (1, 'Army Navy', 5.80, 1, '20746', 'Academy Ave', '8853', null, 'Suitland', 4, 1, UTC_TIMESTAMP, UTC_TIMESTAMP),
       (2, 'Momochi', 7.30, 3, '028-6934', 'Jobojimachi Sawada', '453', null, 'Ninohe-shi', 5, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);

INSERT IGNORE INTO t_payment_methods (id, description)
VALUES (1, 'Credit Card'), (2, 'Debit Card'), (3, 'Cash');

INSERT IGNORE INTO t_restaurants_payment_methods (restaurant_id, payment_method_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 3);

INSERT IGNORE INTO t_products (id, name, description, price, active, restaurant_id)
VALUES (1, 'Apple Pie', 'The simple combination of sugar, buttery pastry and tart sliced apples', 19.00, 1, 1),
       (2, 'Hamburger', 'A sandwich consisting of one or more cooked patties of ground meat', 22.50, 1, 1),
       (3, 'Bagel and Lox', 'A fillet of brined salmon. Lox is one type of salmon product served on a bagel with cream cheese', 25.80, 1, 1),
       (4, 'Sushi', 'Japanese dish of prepared vinegared rice, usually with some sugar and salt', 17.50, 1, 2),
       (5, 'Sashimi', 'A Japanese delicacy consisting of fresh raw fish or meat sliced into thin pieces', 18.80, 1, 2),
       (6, 'Tempura', 'A typical Japanese dish usually consisting of seafood, meat, and vegetables', 16.75, 1, 2);
       
INSERT IGNORE INTO t_groups (id, name)
VALUES (1, 'Admin'), (2, 'Developer');

INSERT IGNORE INTO t_permissions (id, name, description)
VALUES (1, 'Merge', 'Merge code'),
       (2, 'Review', 'Review code and write comments'), 
       (3, 'OPR', 'Open pull requests'), 
       (4, 'APR', 'Approve pull requests');

INSERT IGNORE INTO t_groups_permissions (group_id, permission_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (2, 2), (2, 3);

INSERT INTO t_orders (code, shipping_fee, subtotal, grand_total, address_apartment, address_neighborhood, address_number, address_street_name, address_zip_code, address_city_id, restaurant_id, payment_method_id, user_id, status, confirmed_at, delivered_at, canceled_at, created_at)
VALUES ('e84397d9-e1aa-4116-b110-f032c0a13b16', 5.80, 19.00, 24.80, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'CREATED', NULL, NULL, NULL, '2020-06-24 01:51:57'),
       ('b2320dd2-7443-48f1-8d6c-e1946328e200', 5.80, 38.00, 43.80, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'CONFIRMED', NULL, NULL, NULL, '2020-06-24 03:52:02'),
       ('1564efcb-7370-44eb-a353-f1a311624350', 5.80, 22.50, 28.30, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-23 16:52:09'), 
       ('c31cf1bd-2789-4869-947c-4a4c8804284f', 5.80, 112.50, 118.30, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-23 12:52:12'), 
       ('2798e659-4585-4da9-abce-5e7ab4a34b53', 5.80, 48.30, 54.10, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-23 07:52:24'), 
       ('e3048012-25eb-4ca4-a43f-9eef9d84f769', 5.80, 79.50, 85.30, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-22 09:52:31'), 
       ('4794595d-aeb8-44f9-ab0a-6bab6af87e23', 5.80, 105.30, 111.10, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-22 15:52:41'), 
       ('393b23f3-5bbb-46dc-8d65-a08755f00237', 5.80, 134.40, 140.20, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 1, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-22 18:52:56'), 
       ('d7265e99-61b8-4b5b-a982-7962d9af89df', 7.30, 17.50, 24.80, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-21 22:54:18'),
       ('b2ceffba-b600-42ec-bbbf-7542aa144157', 7.30, 35.00, 42.30, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-20 00:54:22'),
       ('718b9113-e064-43b2-809b-7099bce3f26b', 7.30, 85.25, 92.55, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-18 23:54:30'), 
       ('a506dc88-6e27-4f49-b99f-de0465604aa5', 7.30, 36.30, 43.60, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-18 20:54:42'), 
       ('0b7d95b1-4f39-4c97-b0f4-4f866f47bd92', 7.30, 53.05, 60.35, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1,'DELIVERED', NULL, NULL, NULL, '2020-06-18 19:54:49'), 
       ('74f8710a-9460-4c5e-b0ee-020916ece43f', 7.30, 124.90, 132.20, NULL, 'Lorem', '900', 'Lorem', '9999', 1, 2, 3, 1, 'DELIVERED', NULL, NULL, NULL, '2020-06-17 10:54:55'); 