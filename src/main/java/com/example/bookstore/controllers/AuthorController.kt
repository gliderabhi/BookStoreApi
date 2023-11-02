package com.example.bookstore.controllers

import com.example.bookstore.api.model.mappers.MapAuthorAndAuthorResponse
import com.example.bookstore.api.model.request.CreateAuthorRequest
import com.example.bookstore.api.model.response.AuthorResponse
import com.example.bookstore.api.model.response.BookResponse
import com.example.bookstore.api.model.response.CreateEntityResponse
import com.example.bookstore.models.Author
import com.example.bookstore.services.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping(value = ["/author"])
class AuthorController(
    val authorService: AuthorService
) {
    @RequestMapping(method = [RequestMethod.GET])
    fun getAuthorList(): List<AuthorResponse> {
        val list = authorService.list()
        val authorsResponseList = list.map {
            MapAuthorAndAuthorResponse().mapAuthorToAuthorResponse(it)
        }.toList()
        return authorsResponseList
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun getAuthor(@PathVariable(name = "id") id: Int?): AuthorResponse {
        val author = authorService.findById(id ?: 0)
        return MapAuthorAndAuthorResponse().mapAuthorToAuthorResponse(author)
    }

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody request: @Valid CreateAuthorRequest?): CreateEntityResponse {
        val id = authorService.create(request)
        return CreateEntityResponse(id)
    }

    @RequestMapping(method = [RequestMethod.DELETE])
    fun deleteAuthor(@RequestParam(name = "id") id: @NotNull Int?) {
        authorService.delete(id!!)
    }
}