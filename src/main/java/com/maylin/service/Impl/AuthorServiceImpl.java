package com.maylin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.mapper.IAuthorMapper;
import com.maylin.model.Author;
import com.maylin.repository.IAuthorRepository;
import com.maylin.service.IAuthorService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
	
	
	private final IAuthorRepository authorRepository;
	private final IAuthorMapper authorMapper;

	@Override
	public DtoAuthorResponse saveAuthor(DtoAuthorRequest request) {
		
		String cleanedFirstName=request.getFirstName().trim().toLowerCase();
		String cleanedLastName=request.getLastName().toLowerCase();
		if(authorRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(cleanedFirstName,cleanedLastName)) {
			throw new RuntimeException("Bu bilgilere sahip yazar var.");
			//TODO:Exception fırlatılacak
		}
		
		Author author=authorMapper.toEntity(request);
		author.setFirstName(author.getFirstName().trim());
		author.setLastName(author.getLastName().trim());
		Author dbAuthor=authorRepository.save(author);
	
		return authorMapper.toDtoAuthorResponse(dbAuthor);
	}

	@Override
	public List<DtoAuthorResponse> getAllAuthors() {
		List<Author> authors=authorRepository.getAllAuthors();
		
		return authorMapper.toDtoList(authors);
		
		
	}
	
	@Override
	public DtoAuthorResponse getAuthorById(Long id) {
		
		 Author author=authorRepository.findAuthorWithBooks(id)
				.orElseThrow(() -> new RuntimeException("Bu idye sahıp yazar yok"));
		//TODO:Özel exception fırlat.
		 
		 return authorMapper.toDtoAuthorResponse(author);
		 
	}
	
	

}
