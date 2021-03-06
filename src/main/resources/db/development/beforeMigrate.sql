SET foreign_key_checks = 0;

TRUNCATE TABLE t_states;
TRUNCATE TABLE t_cities;
TRUNCATE TABLE t_cuisines;
TRUNCATE TABLE t_restaurants;
TRUNCATE TABLE t_products;
TRUNCATE TABLE t_payment_methods;
TRUNCATE TABLE t_restaurants_payment_methods;
TRUNCATE TABLE t_users;
TRUNCATE TABLE t_permissions;
TRUNCATE TABLE t_groups;
TRUNCATE TABLE t_users_groups;
TRUNCATE TABLE t_orders;
TRUNCATE TABLE t_order_items;
TRUNCATE TABLE t_groups_permissions;
TRUNCATE TABLE t_users_groups;
TRUNCATE TABLE t_product_photos;
TRUNCATE TABLE oauth_client_details;

SET foreign_key_checks = 1;
