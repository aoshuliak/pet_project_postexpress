INSERT INTO users (first_name, last_name, email, password, role) VALUES ('Mike', 'Brown', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 'ADMIN');
INSERT INTO users (first_name, last_name, email, password, role) VALUES ('Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 'USER');
INSERT INTO users (first_name, last_name, email, password, role) VALUES ('Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 'USER');

INSERT INTO packages (name, description, recipient_id, addresser_id, status) VALUES ('Package 1', 'pack1', 1, 2, 'SENT');
INSERT INTO packages (name, description, recipient_id, addresser_id, status) VALUES ('Package 2', 'pack2', 2, 3, 'DELIVERED');
INSERT INTO packages (name, description, recipient_id, addresser_id, status) VALUES ('Package 3', 'pack3', 3, 1, 'RECEIVED');
INSERT INTO packages (name, description, recipient_id, addresser_id, status) VALUES ('Package 4', 'pack4', 3, 2, 'COMING');

