CREATE TABLE t_groups_permissions (
    group_id int NOT NULL,
    permission_id int NOT NULL,
    PRIMARY KEY (group_id, permission_id),
    KEY FKasx6vp7ra2uc712nmv944x86p (permission_id),
    KEY FKeocppyeujy1a1r4ror2uase7h (group_id),
    CONSTRAINT fk_t_groups_permissions_group_id FOREIGN KEY (group_id) REFERENCES t_groups (id),
    CONSTRAINT fk_t_groups_permissions_permission_id FOREIGN KEY (permission_id) REFERENCES t_permissions (id)
)