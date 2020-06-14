INSERT INTO states (id, name)
VALUES (1, 'Rio Grande do Sul'), (2, 'Santa Catarina'), (3, 'São Paulo'), (4, 'New York'), (5, 'Honshu');

INSERT INTO cities (id, name, state_id) 
VALUES (1, 'Novo Hamburgo', 1), (2, 'Florianópolis', 2), (3, 'São Paulo', 3), (4, 'Maryland', 4), (5, 'Iwate', 5);

INSERT INTO kitchens (id, name)
VALUES (1, 'American'), (2, 'Mexican'), (3, 'Japanese');

INSERT INTO restaurants (id, name, shipping_fee, kitchen_id, address_zip_code, address_street_name, address_number, address_apartment, address_neighborhood, address_city_id)
VALUES (1, 'Army Navy', 5.80, 1, '20746', 'Academy Ave', '8853', null, 'Suitland', 4),
       (2, 'Momochi', 7.30, 3, '028-6934', 'Jobojimachi Sawada', '453', null, 'Ninohe-shi', 5);

INSERT INTO payment_methods (id, description)
VALUES (1, 'Credit Card'), (2, 'Debit Card'), (3, 'Cash');

INSERT INTO permissions (id, name, description)
VALUES (1, 'Admin', 'Allowed to perform all the operations');

INSERT INTO restaurants_payment_methods (restaurant_id, payment_method_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 3);