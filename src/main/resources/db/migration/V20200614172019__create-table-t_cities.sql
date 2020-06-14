CREATE TABLE IF NOT EXISTS t_cities (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(75) NOT NULL,
    state_id INT NOT NULL,
    CONSTRAINT fk_t_cities_t_states FOREIGN KEY (state_id) REFERENCES t_states (id)
);