CREATE TABLE clients (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         document_id VARCHAR(30) NOT NULL,
                         full_name   VARCHAR(150) NOT NULL,
                         phone       VARCHAR(30),
                         email       VARCHAR(150),
                         address     VARCHAR(200),
                         user_id     BIGINT NOT NULL,
                         CONSTRAINT fk_clients_user FOREIGN KEY (user_id) REFERENCES users(id),
                         CONSTRAINT uq_clients_document UNIQUE (document_id),
                         CONSTRAINT uq_clients_email UNIQUE (email)
);
