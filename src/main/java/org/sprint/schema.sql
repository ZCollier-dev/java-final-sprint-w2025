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
-- adminPass, trainerPass, memberPass
INSERT INTO users (username, password, email, phone_no, address, role)
VALUES ('root', '$2a$10$3ppgHTarUoMK5mM2oCHjZu/Y5wtjVJSgexfAAEpt/elDgj5C8CcDi', 'admin@example.com', '800-800-8008', '13 Example Street', 'Admin'),
        ('trainerExample', '$2a$10$3gb8vUzIkCyP5dEajVvggOlSHSq1CesleHjMZsxoLC5lDzdBszqB.', 'trainer@example.com', '080-080-0800', '14 Example Avenue', 'Trainer'),
        ('memberExample', '$2a$10$SBLROSHi3gkdu5AD0jS/8uj6WEWmLaiuOYOmyKfbGQwYJfHDvaCCe', 'member@example.com', '008-008-0080', '15 Example Boulevard', 'Member');

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

INSERT INTO memberships (type, description, cost, member_id, purchase-date)
VALUES ('Single', 'Single Gym Membership to Example Gym', 100.00, 2, '2024-10-28'),
        ('Multi', 'Multi Gym Membership to Example Gym', 150.00, 3, '2024-10-28');

-- WORKOUTCLASSES
CREATE TABLE workoutclasses (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    trainer_id INT NOT NULL,
    schedule VARCHAR(100),
    CONSTRAINT fk_trainer FOREIGN KEY (trainer_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO workoutclasses (type, description, trainer_id, schedule)
VALUES ('Cardio', 'Cardio Workout with Example Trainer', 2, 'Monday 2PM-3PM, Wednesday 2PM-3PM, Friday 2PM-3PM');
