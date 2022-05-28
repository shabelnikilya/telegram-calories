CREATE TABLE IF NOT EXISTS food_products (
    food_id INT REFERENCES food(id),
    product_id INT REFERENCES products(id)
);