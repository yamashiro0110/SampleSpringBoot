SET NAMES utf8mb4;

delete from sample_table;
INSERT INTO sample_table(sample_id, sample_name, post, created) VALUES
(100, 'hoge', 'pen', now()),
(101, 'ホゲ', 'pen', now()),
(102, 'ほげ', 'pen', now()),
(103, '🍣', 'pen', now()),
(104, '🍣', 'pen', now()),
(105, '🍣すし🍺ビール', 'pen', now());
