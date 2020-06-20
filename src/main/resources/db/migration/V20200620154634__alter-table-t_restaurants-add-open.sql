ALTER TABLE t_restaurants ADD open tinyint(1) NOT NULL;
UPDATE t_restaurants SET active = 1;