CREATE TABLE IF NOT EXISTS users_food (
    users_name TEXT REFERENCES users(user_name),
    food_id INT REFERENCES food(id)
);