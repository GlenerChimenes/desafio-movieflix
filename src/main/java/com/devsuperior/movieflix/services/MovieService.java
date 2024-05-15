package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService  {

	@Autowired
	private MovieRepository repository;
	

	public Page<MovieCardDTO> findAll(Pageable pageable) {
		Page<Movie> pageMovies = repository.findAll(pageable);
		return pageMovies.map(x -> new MovieCardDTO(x));
	}

	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDetailsDTO(entity);
	}


	public Page<MovieCardDTO> findAllPaged(Integer genriId, Pageable pageable) {
		Page<Movie> pageMovies;
		if(genriId.equals(0)) {
			 pageMovies = repository.findAll(pageable);
		}else {
			 pageMovies = repository.searchMovies(genriId, pageable);
		}
		return pageMovies.map(x -> new MovieCardDTO(x));
	}
}
