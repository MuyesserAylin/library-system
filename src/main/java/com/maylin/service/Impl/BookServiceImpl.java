package com.maylin.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoAuthorSummary;
import com.maylin.dto.DtoBookCategoryUpdate;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookUpdate;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.enums.ErrorCode;
import com.maylin.enums.Status;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IAuthorMapper;
import com.maylin.mapper.IBookItemMapper;
import com.maylin.mapper.IBookMapper;
import com.maylin.mapper.ICategoryMapper;
import com.maylin.model.Author;
import com.maylin.model.Book;
import com.maylin.model.BookItem;
import com.maylin.model.Category;
import com.maylin.repository.IAuthorRepository;
import com.maylin.repository.IBookRepository;
import com.maylin.repository.ICategoryRepository;
import com.maylin.service.IBookService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
	
	private final IBookRepository bookRepository;
	private final IAuthorRepository authorRepository;
	private final ICategoryRepository categoryRepository;
	private final IBookMapper bookMapper;
	private final ICategoryMapper categoryMapper;
	private final IAuthorMapper authorMapper;
	private final IBookItemMapper bookItemMapper;

	@Override
	public DtoBookResponse saveBook(DtoBookRequest request) {
		
		boolean exists=bookRepository.existsByISBN(request.getISBN());
		if(exists) {
			throw new BaseException(ErrorCode.BOOK_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		Author author=authorRepository.findById(request.getAuthorId())
				.orElseThrow(()->new BaseException(ErrorCode.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		//dbde buldugumuz categorıler
		List<Category> foundCategories=categoryRepository.findAllById(request.getCategoryIds());
		
		
		Set<Long> requestIds=request.getCategoryIds();//kullanıcın  gonderıdgı idler
		
		if(requestIds.size()>foundCategories.size()) {
			Set<Long> missingIds=new HashSet<Long>(request.getCategoryIds());
			Set<Long> foundIds=foundCategories.stream()
					.map(Category::getId).collect(Collectors.toSet());
			
			missingIds.removeAll(foundIds);
			//missingIds kullanıcının gonderıgı ama verıtabanında bulamamdıgımız idleri tutar
			
			throw new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND,missingIds);
			
		}
		
		Book book= bookMapper.toEntity(request);
		book.setTitle(StringUtil.formatTitle(request.getTitle()));
		book.setISBN(StringUtil.formatISBN(request.getISBN()));
		book.setAuthor(author);
		book.setCategories(foundCategories);
		Book savedBook=bookRepository.save(book);
		
		 return buildBookResponse(savedBook);
		
	}

	@Override
	public DtoBookResponse getBookById(Long id) {
		
        Book book=findBookByIdWithDetails(id);
        return buildBookResponse(book);
        
	}
	
	@Override
	public List<DtoBookListResponse> getAllBooks() {
		List<Book> books=bookRepository.findAllWithCategories();
		return books.stream()
				.map(this::buildBookListResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	public void deleteBook(Long id) {
		Book book=findBookByIdWithDetails(id);
		
		boolean hasActiveLoan=book.getBookItems().stream()
				.anyMatch(bookItem ->bookItem.getStatus()==Status.BORROWED);
		
		if(hasActiveLoan) {
			throw new BaseException(ErrorCode.BOOK_HAS_ACTIVE_LOAN,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		book.getCategories().clear();
		bookRepository.save(book);
		bookRepository.delete(book);
		
	}
	
	@Override
	public DtoBookResponse updateBook(Long id, DtoBookUpdate updateBook) {
		Book book=findBookByIdWithDetails(id);
		String title=book.getTitle();
		String ISBN=book.getISBN(); 
		
		if(isNotBlank(updateBook.getTitle())) {
			title=StringUtil.formatTitle(updateBook.getTitle());
		}
		
		if(isNotBlank(updateBook.getISBN())) {
			ISBN=StringUtil.formatISBN(updateBook.getISBN());
			boolean exists=bookRepository.existsByISBN(ISBN);
			
			if(exists && !book.getISBN().equals(ISBN)) {
				throw new BaseException(ErrorCode.BOOK_ALREADY_EXISTS,HttpStatus.CONFLICT);
			}
		}
		
		
		if(updateBook.getAuthorId()!=null) {
			Author author=authorRepository.findById(updateBook.getAuthorId())
					.orElseThrow(()->new BaseException(ErrorCode.AUTHOR_NOT_FOUND,HttpStatus.NOT_FOUND));
			book.setAuthor(author);
		}
		
		book.setTitle(title);
		book.setISBN(ISBN);
		Book savedBook=bookRepository.save(book);
		return buildBookResponse(savedBook);
		
	}
	
	@Override
	public DtoBookResponse updateBookCategories(Long id, DtoBookCategoryUpdate updateCategory) {
		
		Book book=findBookByIdWithCategories(id);
		List<Category> foundCategories=categoryRepository.findAllById(updateCategory.getCategoryIds());
			Set<Long> requestIds=updateCategory.getCategoryIds();
			if(foundCategories.size()<requestIds.size()) {
				Set<Long> missingIds=new HashSet<Long>(updateCategory.getCategoryIds());
				Set<Long> findIds=foundCategories.stream()
						.map(Category ::getId)
						.collect(Collectors.toSet());
				missingIds.removeAll(findIds);
				
				throw new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND,missingIds);
				
			}
			
			book.getCategories().clear();
			book.setCategories(foundCategories);
		
		Book savedBook=bookRepository.save(book);
		return buildBookResponse(savedBook);
	
	}
	

	@Override
	public DtoBookResponse addBookCategory(Long id, Long categoryId) {
		
		Book book=findBookByIdWithDetails(id);
		Category dbCategory=categoryRepository.findById(categoryId)
				.orElseThrow(()->new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		boolean exists=book.getCategories().stream()
		        .anyMatch(category->category.getId().equals(dbCategory.getId()));
		
		if(exists) {
			throw new BaseException(ErrorCode.CATEGORY_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		
		book.getCategories().add(dbCategory);
		Book savedBook=bookRepository.save(book);
		
		return buildBookResponse(savedBook);
		
	}

	@Override
	public DtoBookResponse removeBookCategory(Long id, Long categoryId) {
		Book book=findBookByIdWithDetails(id);
		Category dbCategory=categoryRepository.findById(categoryId)
				.orElseThrow(()->new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		boolean exists=book.getCategories().stream()
		        .anyMatch(category->category.getId().equals(dbCategory.getId()));
		
		if(!exists) {
			throw new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		
		if(book.getCategories().size()==1) {
			throw new BaseException(ErrorCode.BOOK_MUST_HAVE_CATEGORY,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		book.getCategories().removeIf(category->category.getId().equals(dbCategory.getId()));
		Book savedBook=bookRepository.save(book);
		return buildBookResponse(savedBook);
		
	}
	

	
	private boolean isNotBlank(String value) {
		return value!=null && !value.isBlank();
	}

	
	private Book findBookByIdWithDetails(Long id) {
		Book bookWithCategories=findBookByIdWithCategories(id);
		
		Book bookWithItems=bookRepository.findByIdWithBookItems(id)
				.orElseThrow(()->new BaseException(ErrorCode.BOOK_NOT_FOUND,HttpStatus.NOT_FOUND));
		
		bookWithCategories.setBookItems(bookWithItems.getBookItems());
		return bookWithCategories;
		
	}
	
	
	private Book findBookByIdWithCategories(Long id) {
		
		return bookRepository.findByIdWithCategories(id)
				.orElseThrow(()->new BaseException(ErrorCode.BOOK_NOT_FOUND,HttpStatus.NOT_FOUND));

	}
	
	
	private DtoBookResponse buildBookResponse(Book book) {
		DtoBookResponse response=bookMapper.toDtoBookResponse(book);
		response.setAuthor(authorMapper.toDtoAuthorSummary(book.getAuthor()));
		response.setCategories(categoryMapper.toCategoryShortResponseList(book.getCategories()));
		response.setBookItems(bookItemMapper.todtoBookShortList(book.getBookItems()));
		 return response;
	}
	
	private DtoBookListResponse buildBookListResponse(Book book) {
		DtoBookListResponse response=bookMapper.toDtoBookResponseList(book);
		response.setAuthor(authorMapper.toDtoAuthorSummary(book.getAuthor()));
		response.setCategories(categoryMapper.toCategoryShortResponseList(book.getCategories()));
		return response;		
	}
	
}
