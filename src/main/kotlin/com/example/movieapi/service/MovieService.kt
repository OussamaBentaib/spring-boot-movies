package com.example.movieapi.service

import com.example.movieapi.dto.MovieDTO
import com.example.movieapi.repository.MovieRepository
import com.example.movieapi.utils.mapper.MovieMapper
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MovieService (
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) {
    fun createMovie(movieDTO: MovieDTO): MovieDTO {
       try {
           val movie = movieMapper.toEntity(movieDTO)

           movieRepository.save(movie)
           return movieMapper.fromEntity(movie)
       }catch (exception: Exception){
           print(exception)
           throw exception
       }

    }

    fun getMovies(): List<MovieDTO> {
        return movieRepository.findAll().map { movie -> movieMapper.fromEntity(movie)  }
    }

    fun deleteMovie(id: Int) {
       try {
           return movieRepository.deleteById(id)
       } catch (exception: Exception){

       }
    }

    fun updateMovie(movie: MovieDTO): MovieDTO {
        if (movieRepository.findById(movie.id).isPresent){
           movieRepository.save(movieMapper.toEntity(movie))
            return movie
        }
         throw EntityNotFoundException("Movie with id ${movie.id} does not exist")
    }
}