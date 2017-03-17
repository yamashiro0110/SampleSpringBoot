DROP TABLE IF EXISTS post;
CREATE TABLE post (
    post_id BIGINT AUTO_INCREMENT NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY(post_id)
);

DROP TABLE IF EXISTS zipcode;
CREATE TABLE zipcode (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code int NOT NULL,
    zipcode_old int NOT NULL,
    zipcode int NOT NULL,
    prefectures_kana varchar(255) NOT NULL,
    city_kana varchar(255) NOT NULL,
    town_kana varchar(255) NOT NULL,
    prefectures varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    town varchar(255) NOT NULL,
    FULLTEXT prefectures_index(
        prefectures,
        city,
        town,
        prefectures_kana,
        city_kana,
        town_kana
    )
);
