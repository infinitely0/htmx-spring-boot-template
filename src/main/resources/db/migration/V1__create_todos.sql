CREATE TABLE todo
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    completed   BOOLEAN,
    created_at  TIMESTAMP
);
