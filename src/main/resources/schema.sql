CREATE TABLE IF NOT EXISTS transactions (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount     DOUBLE NOT NULL,
    category   VARCHAR(100),
    user_id    VARCHAR(255),
    created_at TIMESTAMP
);
