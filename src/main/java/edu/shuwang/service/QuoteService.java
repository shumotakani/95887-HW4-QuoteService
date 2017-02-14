package edu.shuwang.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.shuwang.model.Author;
import edu.shuwang.model.Quote;

@Service
public interface QuoteService extends CrudRepository<Quote, Long>, QuoteServiceCustom {
    
    Quote[] findByAuthor(Author author);
}
