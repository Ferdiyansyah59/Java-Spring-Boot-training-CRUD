CREATE TABLE article (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(100) NOT NULL,
    published BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);