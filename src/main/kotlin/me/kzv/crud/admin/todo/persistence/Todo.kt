package me.kzv.crud.admin.todo.persistence

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Entity
class Todo(
    // constructor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null,

    @Column(nullable = false)
    @ColumnDefault("false")
    var completed: Boolean = false,

    @Column(nullable = false)
    var todoWork: String
) {}