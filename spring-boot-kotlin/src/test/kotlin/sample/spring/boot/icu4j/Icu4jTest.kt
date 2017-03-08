package sample.spring.boot.icu4j

import com.ibm.icu.text.Transliterator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

/**
 * Created by yamashiro-r on 2017/03/08.
 *
 * Demo: http://demo.icu-project.org/icu-bin/translit
 */

fun main(args: Array<String>) {
    assertEquals("あいうえお", Transliterator.getInstance("Latin-Hiragana").transliterate("aiueo"))
    assertEquals("アイウエオ", Transliterator.getInstance("Latin-Katakana").transliterate("aiueo"))
    assertEquals("あいうえお", Transliterator.getInstance("Kana-Hira").transliterate("ｱｲｳｴｵ"))
    assertEquals("アイウエオ", Transliterator.getInstance("Hira-Kana").transliterate("ｱｲｳｴｵ"))
    assertEquals("aiueo", Transliterator.getInstance("Kana-Latin").transliterate("アイウエオ"))
    assertEquals("aiueo", Transliterator.getInstance("Hira-Latin").transliterate("あいうえお"))
    assertNotEquals("aiueo", Transliterator.getInstance("Kana-Latin").transliterate("あいうえお"))
}
