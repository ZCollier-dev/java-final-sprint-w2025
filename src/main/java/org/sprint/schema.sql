-- DROP TABLES if they already exist ( helps to reset everything as well)
DROP TABLE IF EXISTS workoutclasses;
DROP TABLE IF EXISTS memberships;
DROP TABLE IF EXISTS users;

-- USERS 
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    role VARCHAR(20) CHECK (role IN ('Admin', 'Trainer', 'Member')) NOT NULL
);

-- MEMBERSHIPS
CREATE TABLE memberships (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    cost DECIMAL(10, 2) NOT NULL,
    member_id INT NOT NULL,
    purchase_date DATE NOT NULL,
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES users(id) ON DELETE CASCADE
);

-- WORKOUTCLASSES
CREATE TABLE workoutclasses (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    trainer_id INT NOT NULL,
    schedule VARCHAR(100),
    CONSTRAINT fk_trainer FOREIGN KEY (trainer_id) REFERENCES users(id) ON DELETE CASCADE
);
