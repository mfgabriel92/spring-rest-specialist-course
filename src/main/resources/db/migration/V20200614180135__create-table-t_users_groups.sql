CREATE TABLE t_users_groups (
    id int NOT NULL,
    user_id int NOT NULL,
    group_id int NOT NULL,
    PRIMARY KEY (id),
    KEY FKj3gir4395wh49jnee3kjxx29r (group_id),
    KEY FK8c3b2vfy9kdjrronifb0itwgb (user_id),
    CONSTRAINT FK8c3b2vfy9kdjrronifb0itwgb FOREIGN KEY (user_id) REFERENCES t_users (id),
    CONSTRAINT FKj3gir4395wh49jnee3kjxx29r FOREIGN KEY (group_id) REFERENCES t_groups (id)
)