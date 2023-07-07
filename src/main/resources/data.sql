INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (1, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 'ADMIN');
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (2, 'Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 'USER');
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (3, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 'USER');

INSERT INTO packages (id, name, description, recipient_id, addresser_id, status) VALUES (1, 'Package 1', 'pack1', 1, 2, 'SENT');
INSERT INTO packages (id, name, description, recipient_id, addresser_id, status) VALUES (2, 'Package 2', 'pack2', 2, 3, 'DELIVERED');
INSERT INTO packages (id, name, description, recipient_id, addresser_id, status) VALUES (3, 'Package 3', 'pack3', 3, 1, 'RECEIVED');
INSERT INTO packages (id, name, description, recipient_id, addresser_id, status) VALUES (4, 'Package 4', 'pack4', 3, 2, 'COMING');


