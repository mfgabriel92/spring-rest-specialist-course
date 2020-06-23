ALTER TABLE t_orders ADD code VARCHAR(36) NOT NULL AFTER id;
ALTER TABLE t_orders ADD CONSTRAINT uk_t_orders_code UNIQUE (code);