ALTER TABLE orders
    ADD COLUMN finished_at TIMESTAMP NULL,
    ADD COLUMN finished_by BIGINT NULL,
    ADD CONSTRAINT fk_orders_finished_by FOREIGN KEY (finished_by) REFERENCES users(id);

ALTER TABLE orders
    ADD COLUMN delivered_at TIMESTAMP NULL,
    ADD COLUMN delivered_by BIGINT NULL,
    ADD CONSTRAINT fk_orders_delivered_by FOREIGN KEY (delivered_by) REFERENCES users(id);

-- opcional: quién recibió el pedido
ALTER TABLE orders
    ADD COLUMN receiver_name VARCHAR(150) NULL;
