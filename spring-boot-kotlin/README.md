# spring-boot-kotlin

`Spring Boot`+`Kotlin`を試してみた

## Requirements

- Redis 2.+ (2.4.10で実行)

## Usage

bootRun

```sh
$ gradle clean bootRun
```

## redis

vagrantのlinuxサーバ上でredisを起動している

設定: `/etc/redis.conf`

起動スクリプト: `/etc/init.d/redis`

## flyway

`gradle tasks`

```sh
Flyway tasks
------------
flywayBaseline - Baselines an existing database, excluding all migrations up to and including baselineVersion.
flywayClean - Drops all objects in the configured schemas.
flywayInfo - Prints the details and status information about all the migrations.
flywayMigrate - Migrates the schema to the latest version.
flywayRepair - Repairs the Flyway metadata table.
flywayValidate - Validate applied migrations against resolved ones (on the filesystem or classpath) to detect accidental changes that may prevent the schema(s) from being recreated exactly. Validation fails if differences in migration names, types or checksums are found, versions have been applied that aren"t resolved locally anymore or versions have been resolved that haven"t been applied yet
```

## credit

- https://fineuploader.com/index.html
- http://materializecss.com/
- https://www.tinymce.com/
- https://flywaydb.org/
