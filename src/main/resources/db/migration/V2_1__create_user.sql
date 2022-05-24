CREATE TABLE IF NOT EXISTS users (
    user_name TEXT PRIMARY KEY,
    first_name TEXT,
    second_name TEXT,
    age INT,
    weight NUMERIC(4, 1),
    isBot BOOLEAN,
    enable BOOLEAN
);