package com.example.bookstore.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "book_stock")
data class BookStock(
    @ManyToOne var book: Book? = null,

    @javax.persistence.Column var isbn: String? = null,

    @Column(updatable = false)
    @CreationTimestamp
    private var bookAddedToShelfAt: LocalDateTime? = null,

    @Column
    @UpdateTimestamp
    private var bookAtShelfUpdatedAt: LocalDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //    @Column(name = "id", unique = true, nullable = false)
    var id: Int? = null

)