package com.example.movieapi.Controller

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    @PostMapping
    fun createMovie(@RequestBody movies: MovieDTO): ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.createMovie(movies), HttpStatus.CREATED)
    }

    @GetMapping
    fun getMovies(): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity(movieService.getMovies(), HttpStatus.OK)
    }
    @DeleteMapping("/{id}")
    fun getMovies(@PathVariable id: Int): ResponseEntity<Void> {
        movieService.deleteMovie(id)
        return ResponseEntity(HttpStatus.OK)
    }
    @PutMapping
    fun updateMovie(@RequestBody movie: MovieDTO): ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.updateMovie(movie), HttpStatus.OK)
    }
}