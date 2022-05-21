CREATE TABLE products (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    product_name varchar(255) DEFAULT NULL,
    amount_available int(10) DEFAULT 0,
    cost int(10) DEFAULT 0,
    seller_id bigint(20) NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP,
    deleted_at datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_products_seller_id FOREIGN KEY (seller_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;