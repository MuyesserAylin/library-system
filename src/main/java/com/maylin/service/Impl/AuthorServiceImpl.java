package com.maylin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;
import com.maylin.enums.ErrorCode;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IAuthorMapper;
import com.maylin.model.Author;
import com.maylin.repository.IAuthorRepository;
import com.maylin.service.IAuthorService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
	
	
	private final IAuthorRepository authorRepository;
	private final IAuthorMapper authorMapper;

	@Override
	public DtoAuthorResponse saveAuthor(DtoAuthorRequest request) {
		
		String cleanedFirstName=StringUtil.formatName(request.getFirstName());
		String cleanedLastName=StringUtil.formatLastName(request.getLastName());
		if(authorRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(cleanedFirstName,cleanedLastName)) {
			throw new BaseException(ErrorCode.AUTHOR_ALREADY_EXISTS,HttpStatus.CONFLICT);
			
		}
		
		Author author=authorMapper.toEntity(request);
		author.setFirstName(cleanedFirstName);
		author.setLastName(cleanedLastName);
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
		
		 Author author=findAuthorById(id);
		 return authorMapper.toDtoAuthorResponse(author);
		 
	}

	@Override
	public void deleteAuthor(Long id) {
		
		Author author=findAuthorById(id);
		
		if(!author.getBooks().isEmpty()) {
			throw new BaseException(ErrorCode.AUTHOR_HAS_BOOKS,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		authorRepository.deleteById(id);
	}
	
	

	@Override
	public DtoAuthorResponse updateAuthor(Long id, DtoAuthorUpdate updateAuthor) {
		
		Author author=findAuthorById(id);
		String fName=author.getFirstName();
		String lName=author.getLastName();
		
		
		if(isNotBlank(updateAuthor.getFirstName())) {
			fName=StringUtil.formatName(updateAuthor.getFirstName());
		}
		
		if(isNotBlank(updateAuthor.getLastName())) {
			lName=StringUtil.formatLastName(updateAuthor.getLastName());
		}
		
		
		Optional<Author> optional=authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(fName.toLowerCase(),lName.toLowerCase());
		
		if(optional.isPresent() && optional.get().getId()!=author.getId()) {
			throw new BaseException(ErrorCode.AUTHOR_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		
		author.setFirstName(fName);
		author.setLastName(lName);
		
		return authorMapper.toDtoAuthorResponse(authorRepository.save(author));
		
	}
	
	
	private Boolean isNotBlank(String value) {
		return value!=null && !value.isBlank();
	}
	
    private Author findAuthorById(Long id) {
		
		return authorRepository.findAuthorWithBooks(id)
				.orElseThrow(() -> new BaseException(ErrorCode.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND));
		
      }
    
    

}
