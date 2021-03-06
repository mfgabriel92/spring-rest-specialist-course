INSERT INTO t_users (id, name, email, password, created_at)
VALUES (1, 'Friedrick Fookes', 'ffookes0@mac.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (2, 'Orson Abdon', 'oabdon1@msn.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (3, 'Cherilyn Giorgio', 'cgiorgio2@umich.edu', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (4, 'Thorvald MacGebenay', 'tmacgebenay1@google.com.hk', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (5, 'Corie Cassidy', 'ccassidy0@typepad.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP),
       (6, 'Fidelity Ivanusyev', 'fivanusyev0@yahoo.com', '$2y$12$FpD4HR3pf7g.xRaF7BK7POxRvcczC84XfKHCHadCF6Q.N86xd/s1q', UTC_TIMESTAMP);

INSERT IGNORE INTO t_states (id, name)
VALUES (1, 'California');

INSERT IGNORE INTO t_cities (id, name, state_id)
VALUES (1, 'City Of Commerce', 1);

INSERT IGNORE INTO t_cuisines (id, name)
VALUES (1, 'American');

INSERT IGNORE INTO t_restaurants (id, name, shipping_fee, cuisine_id, address_zip_code, address_street_name, address_number, address_apartment, address_neighborhood, address_city_id, active, open, created_at, updated_at)
VALUES (1, 'The Brimstone Wok', 5.75, 1, '90040', 'Joy Lane', 1384, null, null, 1, 1, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);

INSERT IGNORE INTO t_restaurants_users (restaurant_id, user_id)
VALUES (1, 4);

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
VALUES (1, 'Read'), (2, 'Read & Write'), (3, 'Read, Write & Delete'), (4, 'Read Reports');

INSERT IGNORE INTO t_permissions (id, name, description)
VALUES (1, 'READ_RESOURCE', 'Read'),
       (2, 'WRITE_RESOURCE', 'Write'),
       (3, 'DELETE_RESOURCE', 'Delete'),
       (4, 'READ_REPORTS_RESOURCE', 'Read reports');

INSERT IGNORE INTO t_groups_permissions (group_id, permission_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 4);

INSERT IGNORE INTO t_users_groups (user_id, group_id)
VALUES (1, 3),
       (2, 2),
       (3, 1),
       (1, 4);

INSERT IGNORE INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('web-client', null, '$2y$12$mk/mJN095YrYi/FY5aDVj.kr3ntdVys.O1aEF.qRfXogoqITG8282', 'READ,WRITE,DELETE', 'password', null, null, 60 * 60, null, null, null),
       ('background-app', null, '$2y$12$mk/mJN095YrYi/FY5aDVj.kr3ntdVys.O1aEF.qRfXogoqITG8282', 'READ,WRITE,DELETE', 'client_credentials', null, null, 60 * 60, null, null, null),
       ('analytics-app', null, '$2y$12$mk/mJN095YrYi/FY5aDVj.kr3ntdVys.O1aEF.qRfXogoqITG8282', 'READ,WRITE,DELETE,REPORTS', 'authorization_code', 'http://another-uri.com', 'REPORTS', 60 * 60, null, null, null),
       ('webadmin', null, '$2y$12$mk/mJN095YrYi/FY5aDVj.kr3ntdVys.O1aEF.qRfXogoqITG8282', 'READ,WRITE,DELETE', 'implicit', null, null, 60 * 60, null, null, null),
       ('introspect', null, '$2y$12$mk/mJN095YrYi/FY5aDVj.kr3ntdVys.O1aEF.qRfXogoqITG8282', null, 'password', null, null, null, null, null, null);
