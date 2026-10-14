CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS processing_log
(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    input_text VARCHAR(255) NOT NULL,
    output_text VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_processing_log_user_id
    ON processing_log(user_id);