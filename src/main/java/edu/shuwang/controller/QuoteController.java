package edu.shuwang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.shuwang.model.Author;
import edu.shuwang.model.Quote;
import edu.shuwang.service.AuthorService;
import edu.shuwang.service.QuoteService;

@RestController
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
        System.out.println("QC random() is running");
        return quoteService.randomQuote();
    }
    
    @RequestMapping("/api/quote/list")
    public Quote[] list(String authorName) {
        //pass the author name into the Controller use CRUD to solve the issue
        System.out.println("You are looking for quotes from " + authorName);
        return quoteService.findByAuthor(authorService.findByName(authorName));
    }
    
    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody Quote quote) {
        
        Author a = authorService.findByName(quote.getAuthor().getName());
        
        if (a == null) {
            System.out.println("----------Saving as a !!!NEW!!! author----------");
            authorService.save(quote.getAuthor());
        } else {
            System.out.println("----------Saving as a !!!OLD!!! author----------");
            quote.setAuthor(a);
        }
        
        System.out.println("Saving quote");
        quoteService.save(quote);
    }
    
    
    public Quote fallback() {
        Quote q = new Quote();
        Author a = new Author("Confucius");
        q.setText("The superior man is modest in his speech, but exceeds in his actions.");
        q.setAuthor(a);
        return q; 
    }
}
