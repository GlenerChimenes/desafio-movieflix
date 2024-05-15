package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService  {

	@Autowired
	private GenreRepository repository;
	

	public List<GenreDTO> findAll() {
		 List<Genre> entity = repository.findAll();
		 return entity.stream().map(x -> new GenreDTO(x)).toList();
	}
}