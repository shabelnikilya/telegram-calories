CREATE TABLE IF NOT EXISTS products(
    id SERIAL PRIMARY KEY,
    name TEXT,
    proteins NUMERIC(8, 2),
    fats NUMERIC(8, 2),
    carbohydrates NUMERIC(8, 2),
    kcal INT
);