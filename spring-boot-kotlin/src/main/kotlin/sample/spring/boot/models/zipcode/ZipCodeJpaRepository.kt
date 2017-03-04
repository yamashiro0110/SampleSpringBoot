package sample.spring.boot.models.zipcode

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Repository
interface ZipCodeJpaRepository: JpaRepository<ZipCode, Long> {
    @Query(nativeQuery = true,
            value = "select * " +
                    "from zipcode " +
                    "where MATCH(prefectures, city, town, prefectures_kana, city_kana, town_kana) AGAINST(:query) " +
                    "limit 100")
    fun search(@Param("query") query: String): List<ZipCode>
}
