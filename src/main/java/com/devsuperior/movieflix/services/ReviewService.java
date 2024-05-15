package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService  {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MovieService movieService;
	

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		
		UserDTO UserDto = service.getProfile();
		MovieDetailsDTO movieDto = movieService.findById(dto.getMovieId());
		
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new ReviewDTO(entity, UserDto, movieDto);
		
	}

	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setId(dto.getId());
		entity.setText(dto.getText());
	}
}
