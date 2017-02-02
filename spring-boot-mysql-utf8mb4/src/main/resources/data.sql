SET NAMES utf8mb4;

INSERT INTO sample_table(sample_id, sample_name, created) VALUES
(0, 'hoge', now()),
(1, 'ホゲ', now()),
(2, 'ほげ', now()),
(3, '🍺', now()),
(4, '🍣', now()),
(5, '🍣すし🍺ビール', now())
;
