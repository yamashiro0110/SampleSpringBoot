DELETE FROM main_table;

INSERT INTO main_table(id, msg, created) VALUES
(1, 'i have a pen.', now()),
(2, 'i have a apple.', now()),
(3, 'apple pen.', now());
