# spring-boot-multi-project

マルチプロジェクトビルドを試してみた

***

`spring-boot-multiproject-master`が`spring-boot-multiproject-sub`をincludeしている。

```build.gradle
compile project(':spring-boot-multiproject-sub')
```
