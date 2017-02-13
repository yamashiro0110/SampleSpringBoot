DROP TABLE if EXISTS sample_table;

CREATE TABLE sample_table(
    sample_id INT NOT NULL,
    sample_name VARCHAR(50) NOT NULL,
    created DATETIME NOT NULL,
    PRIMARY KEY(sample_id)
);

DROP TABLE if EXISTS sample_text_table;

CREATE TABLE sample_text_table (
    sample_id INT NOT NULL,
    sample_text TEXT NOT NULL,
    PRIMARY KEY(sample_id)
);
