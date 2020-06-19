ALTER TABLE t_restaurants ADD active tinyint(1) NOT NULL;
UPDATE t_restaurants SET active = 1;