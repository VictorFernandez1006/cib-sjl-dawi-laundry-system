-- Catálogo de tipos de servicio (lavado, planchado, etc.)
CREATE TABLE service_types (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               name VARCHAR(100) NOT NULL UNIQUE,
                               price_per_kg DECIMAL(10,2) NOT NULL,
                               enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- Orden/Ingreso
CREATE TABLE orders (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        client_id BIGINT NOT NULL,
                        service_type_id BIGINT NOT NULL,
                        weight_kg DECIMAL(10,2) NOT NULL,
                        unit_price DECIMAL(10,2) NOT NULL,
                        total_price DECIMAL(10,2) NOT NULL,
                        status VARCHAR(30) NOT NULL,     -- REGISTRADO | EN_PROCESO | TERMINADO | ENTREGADO
                        notes VARCHAR(300),
                        assigned_to BIGINT,              -- trabajador (user_id), nullable
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (client_id) REFERENCES clients(id),
                        FOREIGN KEY (service_type_id) REFERENCES service_types(id),
                        FOREIGN KEY (assigned_to) REFERENCES users(id)
);

-- Seed opcional
INSERT INTO service_types (name, price_per_kg) VALUES ('LAVADO', 5.50);
INSERT INTO service_types (name, price_per_kg) VALUES ('PLANCHADO', 4.00);
INSERT INTO service_types (name, price_per_kg) VALUES ('LAVADO+PLANCHADO', 8.50);