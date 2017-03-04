package sample.spring.boot.models.zipcode

import javax.persistence.*

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Entity
@Table(name = "zipcode")
class ZipCode(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0L,
    @Column(name = "zipcode_old")
    var zipCodeOld: String = "",
    @Column(name = "zipcode")
    var zipCode: String = "",
    @Column(name = "prefectures")
    var prefectures: String = "",
    @Column(name = "city")
    var city: String = "",
    @Column(name = "town")
    var town: String = ""
)
