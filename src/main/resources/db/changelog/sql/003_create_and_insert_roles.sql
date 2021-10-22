INSERT INTO roles (name) VALUE ('Simple');
INSERT INTO roles (name) VALUE ('Blocked');
INSERT INTO roles (name) VALUE ('Admin');
INSERT INTO roles (name) VALUE ('Super Admin');

CREATE TABLE available_roles
(
    role_id           INT,
    available_role_id INT,
    PRIMARY KEY (role_id, available_role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (available_role_id) REFERENCES roles (id)
);

INSERT INTO available_roles (role_id, available_role_id)
    VALUE (3, 1);
INSERT INTO available_roles (role_id, available_role_id)
    VALUE (3, 2);
INSERT INTO available_roles (role_id, available_role_id)
    VALUE (4, 1);
INSERT INTO available_roles (role_id, available_role_id)
    VALUE (4, 2);
INSERT INTO available_roles (role_id, available_role_id)
    VALUE (4, 3);