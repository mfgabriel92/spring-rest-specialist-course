CREATE TABLE t_cities(
    id int NOT NULL AUTO_INCREMENT,
    name varchar (255) NOT NULL,
    state_id int NOT NULL,
    PRIMARY KEY (id),
    KEY FKiy3u3ivljqpk29ywdlsk57k6 (state_id),
    CONSTRAINT FKiy3u3ivljqpk29ywdlsk57k6 FOREIGN KEY (state_id) REFERENCES t_states (id)
)