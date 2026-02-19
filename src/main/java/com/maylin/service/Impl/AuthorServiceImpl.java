package com.maylin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;
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
		
		 Author author=findAuthorById(id);
		 return authorMapper.toDtoAuthorResponse(author);
		 
	}

	@Override
	public void deleteAuthor(Long id) {
		
		Author author=findAuthorById(id);
		
		if(!author.getBooks().isEmpty()) {
			throw new RuntimeException("Silinmek istenen yazarın kıtapları var once onları siliniz.");	
		}
		
		authorRepository.deleteById(id);
	}
	
	

	@Override
	public DtoAuthorResponse updateAuthor(Long id, DtoAuthorUpdate updateAuthor) {
		
		Author author=findAuthorById(id);
		
		if(isNotBlank(updateAuthor.getFirstName())) {
			author.setFirstName(updateAuthor.getFirstName().trim());
		}
		
		if(isNotBlank(updateAuthor.getLastName())) {
			author.setLastName(updateAuthor.getLastName().trim());
		}
		
		String fName=author.getFirstName();
		String lName=author.getLastName();
		
		Optional<Author> optional=authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(fName.toLowerCase(),lName.toLowerCase());
		
		if(optional.isPresent() && optional.get().getId()!=author.getId()) {
				throw new RuntimeException("Bu bilgilere sahip yazar var güncelleme yapamıyoruz.");
				//TODO:Özel exception fırlat
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
				.orElseThrow(() -> new RuntimeException("Bu idye sahip yazar yok."));
		//TODO:BU İdye sahip author yok dıye ozel exception fırlat.
		
      }
    
    

}
