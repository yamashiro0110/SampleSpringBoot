package sample.spring.boot.kuromoji

import com.atilika.kuromoji.ipadic.Token
import com.atilika.kuromoji.ipadic.Tokenizer
import org.junit.Test

/**
 * Created by yamashiro-r on 2017/03/04.
 */
class TokenizerTest {
    private fun textList() = arrayOf(
            "超人気テレビアニメ『けものフレンズ』を作ったキーパーソンに超ロングインタビュー！ ネットでは連日のように各話の考察が行われ、「すごーい！」や「たのしー！」といったセリフは流行語にもなっています。",
            "沖縄だとか騒いでるのは一部のブロガーだけ",
            "寿司ビール問題",
            "10日放送の「中居正広のミになる図書館」（テレビ朝日系）で、SMAPの中居正広が、篠原信一の過去の勘違いを明かす一幕があった。",
            "GlassFishに登録したJDBCリソースを利用するため、dataSourceのtypeをJNDIに設定します。 propertyのdata_sourceの値は、glassfish-resources.xmlで設定しているjndi-nameです。"
    )

    @Test
    fun 全部() {
        textList().map { tokenize(it) }
                .map { it.filter { it.baseForm != "*" }.toList() }
                .forEach {
                    println("****")
                    it.forEach { printToken(it) }
                }
    }

    @Test
    fun 名詞だけ() {
        textList().map { tokenize(it) }
                .map { it.filter { it.partOfSpeechLevel1 == "名詞" && it.baseForm != "*" }.toList() }
                .forEach {
                    println("****")
                    it.forEach { println("基本形; ${it.baseForm}, 読み: ${it.reading}") }
                }
    }

    fun tokenize(text: String) = Tokenizer().tokenize(text)

    fun printToken(token: Token) {
        println("品詞細分類1: ${token.partOfSpeechLevel1}")
        println("品詞細分類2: ${token.partOfSpeechLevel2}")
        println("品詞細分類3: ${token.partOfSpeechLevel3}")
        println("品詞細分類4: ${token.partOfSpeechLevel4}")
        println("活用型: ${token.conjugationType}. ${token.conjugationForm}")
        println("基本形: ${token.baseForm}")
        println("読み: ${token.reading}")
        println("発音: ${token.pronunciation}")
    }

}
