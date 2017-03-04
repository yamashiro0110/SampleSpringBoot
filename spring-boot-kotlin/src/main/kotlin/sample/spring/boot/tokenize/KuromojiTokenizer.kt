package sample.spring.boot.tokenize

import com.atilika.kuromoji.ipadic.Tokenizer

/**
 * Created by yamashiro-r on 2017/03/04.
 */
object KuromojiTokenizer {
    fun tokenize(text: String)= Tokenizer().tokenize(text)

    fun tokenizeString(text: String) = this.tokenize(text).joinToString(",")

    fun baseByTokenize(text: String) = this.tokenize(text).filter {
        it.partOfSpeechLevel1 == "名詞" && it.partOfSpeechLevel1 != "*"
    }
}
