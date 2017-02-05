# spring-boot-i18n

SpringBootで多言語対応。

`src/main/resources/i18n/`以下がメッセージソース

Thymeleafのテンプレートから、`<h1 th:text="#{hello.world}">hello world</h1>`のように参照可能
> `src/main/resources/templates/`以下がテンプレート

メッセージに引数が渡せる
> ex) `<h3 th:text="#{hello.world.msg(${msg})}"></h3>`

Bean Validationのメッセージにも対応可能
> ex) `src/main/java/sample/spring/boot/SampleModel.java`
