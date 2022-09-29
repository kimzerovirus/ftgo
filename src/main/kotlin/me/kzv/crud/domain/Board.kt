package me.kzv.crud.domain

import javax.persistence.*

@Entity
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    var id: Long? = null,

    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @ElementCollection(fetch = FetchType.LAZY)
    val uploadFiles: List<String>,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

): BaseEntity()