# spring-boot-mysql-utf8mb4

SpringBootでMySQL5.7+utf8mb4を扱う。

## Usage

```sh
$ gradle clean bootRun
```

ex) 実行するとinsertしたデータを表示する

```
2017-02-01 00:19:52.331  INFO 24876 --- [  restartedMain] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
SampleTable(id=0, name=hoge, created=2017-02-01 00:19:48.0)
SampleTable(id=1, name=ホゲ, created=2017-02-01 00:19:48.0)
SampleTable(id=2, name=ほげ, created=2017-02-01 00:19:48.0)
SampleTable(id=3, name=🍺, created=2017-02-01 00:19:48.0)
SampleTable(id=4, name=🍣, created=2017-02-01 00:19:48.0)
2017-02-01 00:19:52.634  INFO 24876 --- [  restartedMain] sample.spring.boot.Application           : Started Application in 10.626 seconds (JVM running for 11.286)
```
