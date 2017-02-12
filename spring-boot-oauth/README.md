# spring-boot-oauth

OAuthログインのsample

## OAuth Settings

環境変数にappIdとappSecretを設定

```sh
$ export SAMPLE_OAUTH_GOOGLE_APIKEY="xxx"
$ export SAMPLE_OAUTH_GOOGLE_SECRET="xxx"
$ export SAMPLE_OAUTH_YAHOOJAPAN_APIKEY="xxx"
$ export SAMPLE_OAUTH_YAHOOJAPAN_SECRET="xxx"
```

or

`src/main/resources/application-oauth.properties`を作成して設定を記述する
> `application.properties`でincludeしてる

```
sample.oauth.google.apikey=xxx
sample.oauth.google.secret=xxx
sample.oauth.yahooJapan.apikey=xxx
sample.oauth.yahooJapan.secret=xxx
```

## Usage

アプリケーション起動

```sh
$ ./gradlew bootRun
```

`localhost:8080`にアクセスする
