insert into post (post_id, content, created, updated) values
(1, 'hogehoge', now(), now()),
(2, 'sushi', now(), now()),
(3, 'beer', now(), now());

LOAD DATA LOCAL INFILE "src/main/resources/data/KEN_ALL-utf8.CSV"
INTO TABLE zipcode CHARACTER SET utf8mb4
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(@1,@2,@3,@4,@5,@6,@7,@8,@9,@10,@11,@12,@13,@14,@15)
SET code=@1,
    zipcode_old=@2,
    zipcode=@3,
    prefectures_kana=@4,
    city_kana=@5,
    town_kana=@6,
    prefectures=@7,
    city=@8,
    town=@9;
