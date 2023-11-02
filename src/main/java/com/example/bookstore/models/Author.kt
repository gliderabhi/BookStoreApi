package com.example.bookstore.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
//@DynamicUpdate
@Table(name = "authors")
data class Author(
    @Column
     var firstName: @NotBlank String? = null,

    @Column
    var lastName: @NotBlank String? = null,

    @Column
     var birthDate: LocalDate? = null,

    @ManyToMany(mappedBy = "bookAuthors", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("bookAuthors")
     val authorBooks: Set<Book>? = null,

    @Column(updatable = false)
    @CreationTimestamp
     var createdAt: LocalDateTime? = null,

    @Column
    @UpdateTimestamp
     var updatedAt: LocalDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //    @Column(name = "id", unique = true, nullable = false)
    var id: Int? = null
)