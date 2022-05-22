CREATE TABLE coin_frequencies (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    value int(10) DEFAULT 0,
    frequency int(10) DEFAULT 0,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP,
    deleted_at datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_currency_frequencies_amount (value)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;