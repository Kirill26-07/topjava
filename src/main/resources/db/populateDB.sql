DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM role;
DELETE FROM meal;

INSERT INTO users (id, name, email, password) VALUES
  (DEFAULT, 'User', 'user@yandex.ru', 'password'),
  (DEFAULT, 'Admin', 'admin@gmail.com', 'admin');

INSERT INTO role (id, role) VALUES (DEFAULT, 'ROLE_USER'), (DEFAULT, 'ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES (5, 5), (6, 6);

INSERT INTO meal(id, date, description, calories) VALUES
  (DEFAULT, '2018-10-10', 'Завтрак', 500),
  (DEFAULT, '2018-10-11', 'Обед', 800),
  (DEFAULT, '2018-10-12', 'Ужин', 1200),
  (DEFAULT, '2018-10-12', 'Завтрак', 1800),
  (DEFAULT, '2018-10-10', 'Обед', 2000),
  (DEFAULT, '2018-10-11', 'Ужин', 800),
  (DEFAULT, '2018-10-12', 'Обед', 1300);

INSERT INTO user_meals(user_id, meal_id) VALUES
  (5, 1), (5, 2), (5, 3), (5, 4), (6, 5), (6, 6), (6, 7);