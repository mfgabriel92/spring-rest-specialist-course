INSERT INTO `kitchens` (`id`, `name`) VALUES (1, 'American'), (2, 'Mexican'), (3, 'Japanese');

INSERT INTO `restaurants` (`id`, `name`, `shipping_fee`, `kitchen_id`) VALUES (1, 'Army Navy', 5.75, 1), (2, 'El Toro', 4.80, 2), (3, 'Tatsu Maki', 3.90, 3);

INSERT INTO `payment_methods` (`id`, `description`) VALUES (1, 'Credit Card'), (2, 'Debit Card'), (3, 'Cash Card');

INSERT INTO `permissions` (`id`, `name`, `description`) VALUES (1, 'Admin', 'Allowed to perform all the operations');

INSERT INTO `states` (`id`, `name`) VALUES (1, 'Rio Grande do Sul'), (2, 'Santa Catarina'), (3, 'São Paulo');

INSERT INTO `cities` (`id`, `name`, `state_id`) VALUES (1, 'Novo Hamburgo', 1), (2, 'Florianópolis', 2), (3, 'São Paulo', 3);