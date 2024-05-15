package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT * FROM tb_movie m WHERE m.genre_id = :genreId", countQuery = "SELECT count(*) FROM tb_movie m WHERE m.genre_id = :genreId", nativeQuery = true)
	Page<Movie> searchMovies(@Param("genreId") Integer genreId, Pageable pageable);
	

}
