DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM role;
DELETE FROM meal;

INSERT INTO users (id, name, email, password) VALUES
  (DEFAULT, 'User', 'user@yandex.ru', 'password'),
  (DEFAULT, 'Admin', 'admin@gmail.com', 'admin');

INSERT INTO role (id, role) VALUES (DEFAULT, 'ROLE_USER'), (DEFAULT, 'ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1), (2, 2);

INSERT INTO meal(id, date, description, calories) VALUES
  (DEFAULT, TIMESTAMP '2018-10-10 10:00:00', 'Завтрак', 500),
  (DEFAULT, TIMESTAMP '2018-10-11 14:00:00', 'Обед', 800),
  (DEFAULT, TIMESTAMP '2018-10-12 20:00:00', 'Ужин', 1200),
  (DEFAULT, TIMESTAMP '2018-10-12 09:00:00', 'Завтрак', 1800),
  (DEFAULT, TIMESTAMP '2018-10-10 12:00:00', 'Обед', 2000),
  (DEFAULT, TIMESTAMP '2018-10-11 21:00:00', 'Ужин', 800),
  (DEFAULT, TIMESTAMP '2018-10-12 13:00:00', 'Обед', 1300);

INSERT INTO user_meals(user_id, meal_id) VALUES
  (1, 1), (1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (2, 7);