-- Tabla de roles
CREATE TABLE roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla de usuarios
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(150) NOT NULL
);

-- Tabla intermedia para la relación muchos a muchos
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Insertar roles por defecto
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('RECEPCIONISTA');
INSERT INTO roles (name) VALUES ('TRABAJADOR');
INSERT INTO roles (name) VALUES ('CLIENTE');
