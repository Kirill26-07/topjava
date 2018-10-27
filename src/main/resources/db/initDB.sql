DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_meals;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meal;

CREATE TABLE users (
  id               SERIAL PRIMARY KEY,
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE role (
  id SERIAL PRIMARY KEY,
  role VARCHAR UNIQUE NOT NULL
);

CREATE TABLE user_roles (
  user_id    INTEGER NOT NULL,
  role_id    INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE meal (
  id            SERIAL PRIMARY KEY,
  date          TIMESTAMP NOT NULL,
  description   VARCHAR  NOT NULL,
  calories      INTEGER NOT NULL
);

CREATE TABLE user_meals (
  user_id INTEGER NOT NULL,
  meal_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id) REFERENCES meal (id) ON DELETE CASCADE
);