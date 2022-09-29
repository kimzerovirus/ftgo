package me.kzv.crud.domain

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
    val isDepth: Boolean, // false: 댓글, true: 대댓글
    val replyGroup: String, // group-uuid

    @ManyToOne(fetch = FetchType.LAZY)
    val board: Board
){

}