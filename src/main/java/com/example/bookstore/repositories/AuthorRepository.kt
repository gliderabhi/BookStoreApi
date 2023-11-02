package com.example.bookstore.repositories

import com.example.bookstore.models.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Int> {
}