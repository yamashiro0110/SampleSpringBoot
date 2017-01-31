drop table if exists sample_table;

create table sample_table(
    sample_id INT NOT NULL PRIMARY KEY,
    sample_name VARCHAR(50) NOT NULL,
    created DATETIME NOT NULL
);
