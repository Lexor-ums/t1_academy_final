CREATE TABLE IF NOT EXISTS limits (
 id BIGSERIAL PRIMARY KEY,
 user_limit NUMERIC(10,2) CONSTRAINT user_limit_positive CHECK (user_limit > 0)
);
