package com.example.bookstore.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Column
    var name: String? = null,

    @Column
    var price: Double = 0.0,

    @Column
    var publishedYear: Int = 0,

    @Column
    var category: String? = null,

    @Column
    var description: String? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "book_author",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "author_id", referencedColumnName = "id")]
    )
    @JsonIgnoreProperties("authorBooks")
    var bookAuthors: Set<Author>? = null,

    @Column(updatable = false)
    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @Column
    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //    @Column(name = "id", unique = true, nullable = false)
    var id: Int? = null
)