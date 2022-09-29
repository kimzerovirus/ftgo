package me.kzv.crud.persistence

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class Reply (

    @Id
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name = "sys-uuid", strategy = "uuid")
    @Column(name = "reply_id")
    var id: String? = null,
    val text: String,
    val replyer: String,
    val depth: Int, // 0: 댓글, 1: 대댓글
    val group: String, // group-uuid

    @ManyToOne(fetch = FetchType.LAZY)
    val board: Board
){

}