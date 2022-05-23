CREATE TABLE orders (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    buyer_id bigint(20) NOT NULL,
    product_id bigint(20) NOT NULL,
    amount_of_product int(10) DEFAULT 0,
    total_cost int(10) DEFAULT 0,
    reference varchar(255) DEFAULT NULL,
    status varchar(255) DEFAULT NULL,
    message varchar(255) DEFAULT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP,
    deleted_at datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),

    CONSTRAINT fk_orders_buyer_id FOREIGN KEY (buyer_id) REFERENCES users (id),
    CONSTRAINT fk_orders_product_id FOREIGN KEY (product_id) REFERENCES products (id)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;