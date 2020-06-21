CREATE TABLE t_users_groups (
    user_id int NOT NULL,
    group_id int NOT NULL,
    PRIMARY KEY (user_id, group_id),
    KEY FKj3gir4395wh49jnee3kjxx29r (group_id),
    KEY FK8c3b2vfy9kdjrronifb0itwgb (user_id),
    CONSTRAINT t_users_groups_user_id FOREIGN KEY (user_id) REFERENCES t_users (id),
    CONSTRAINT t_users_groups_group_id FOREIGN KEY (group_id) REFERENCES t_groups (id)
)