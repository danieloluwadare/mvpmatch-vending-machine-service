CREATE TABLE users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    username varchar(255) DEFAULT NULL,
    password varchar(100) DEFAULT NULL,
    deposit int(10) DEFAULT 0,
    role varchar(100) DEFAULT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_username (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
