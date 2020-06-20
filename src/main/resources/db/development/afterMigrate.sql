INSERT IGNORE INTO t_states (id, name)
VALUES (1, 'Rio Grande do Sul'), (2, 'Santa Catarina'), (3, 'São Paulo'), (4, 'New York'), (5, 'Honshu');

INSERT IGNORE INTO t_cities (id, name, state_id) 
VALUES (1, 'Novo Hamburgo', 1), (2, 'Florianópolis', 2), (3, 'São Paulo', 3), (4, 'Maryland', 4), (5, 'Iwate', 5);

INSERT IGNORE INTO t_kitchens (id, name)
VALUES (1, 'American'), (2, 'Mexican'), (3, 'Japanese');

INSERT IGNORE INTO t_restaurants (id, name, shipping_fee, kitchen_id, address_zip_code, address_street_name, address_number, address_apartment, address_neighborhood, address_city_id, active, created_at, updated_at)
VALUES (1, 'Army Navy', 5.80, 1, '20746', 'Academy Ave', '8853', null, 'Suitland', 4, 1, UTC_TIMESTAMP, UTC_TIMESTAMP),
       (2, 'Momochi', 7.30, 3, '028-6934', 'Jobojimachi Sawada', '453', null, 'Ninohe-shi', 5, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);

INSERT IGNORE INTO t_payment_methods (id, description)
VALUES (1, 'Credit Card'), (2, 'Debit Card'), (3, 'Cash');

INSERT IGNORE INTO t_permissions (id, name, description)
VALUES (1, 'Admin', 'Allowed to perform all the operations');

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