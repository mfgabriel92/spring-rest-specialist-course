CREATE TABLE t_users (
    id int NOT NULL AUTO_INCREMENT,
    created_at datetime NOT NULL,
    email varchar (255) NOT NULL,
    name varchar (255) NOT NULL,
    password varchar (255) NOT NULL,
    PRIMARY KEY (id)
)