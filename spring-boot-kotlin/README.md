# spring-boot-kotlin

`Spring Boot`+`Kotlin`を試してみた

## Usage

executable jar

```sh
$ gradle build
```

```sh
$ cp -f build/libs/spring-boot-kotlin.jar bin/
```

```sh
$ ./bin/spring-boot-kotlin.jar start
```

bootRun

```sh
$ gradle clean bootRun
```

## zip code

郵便番号データテーブルの作成
> DB: mysql5.6

```sql
DROP TABLE IF EXISTS zipcode;

CREATE TABLE zipcode (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code int NOT NULL,
    zipcode_old int NOT NULL,
    zipcode int NOT NULL,
    prefectures varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    town varchar(255) NOT NULL,
    FULLTEXT prefectures_index(prefectures, city, town)
);
```

データをDL
> http://www.post.japanpost.jp/zipcode/dl/readme.html

必要に応じて、文字コードを変換

データを登録する

```sql
LOAD DATA LOCAL INFILE "/vagrant_data/KEN_ALL-utf8.CSV" 
INTO TABLE zipcode
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n' 
(@1,@2,@3,@4,@5,@6,@7,@8,@9,@10,@11,@12,@13,@14,@15)
SET code=@1, 
    zipcode_old=@2, 
    zipcode=@3, 
    prefectures=@4,
    city=@5,
    town=@6;
```
