CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS medications
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100)   NOT NULL,
    description     VARCHAR(255)   NOT NULL,
    price           DECIMAL(12, 2) NOT NULL,
    expiration_date DATE           NOT NULL,
    category_id     BIGINT         NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);