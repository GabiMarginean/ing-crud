CREATE TABLE `user`
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    username      VARCHAR(255) UNIQUE,
    password      VARCHAR(255),
    role          VARCHAR(25),
    created       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_category
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(50) NOT NULL UNIQUE,
    created       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE warehouse
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    address       VARCHAR(255) NOT NULL,
    capacity      BIGINT NOT NULL,
    created       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(50) NOT NULL,
    description   VARCHAR(255),
    price         DOUBLE NOT NULL,
    category_id   BIGINT,
    created       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES product_category (id)
);

CREATE TABLE product_warehouse
(
    product_id     BIGINT,
    warehouse_id   BIGINT,
    quantity       BIGINT,
    PRIMARY KEY (product_id, warehouse_id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
);

INSERT INTO product_category (name)
VALUES
    ('category_1'),
    ('category_2'),
    ('category_3');

INSERT INTO `user` (username, password, role)
VALUES
    ('admin', '$2a$10$YAl.Z5XWuwZK/df.ComRHOVx3cNdMb/lO4xD/izVAxyCvqOJfND1y', 'ADMIN'),
    ('observer', '$2a$10$peGInE6C6babNAGxLEZMrOSWnBhDHiRhEUOBY4L5P01I1qmcDQNeC', 'OBSERVER');