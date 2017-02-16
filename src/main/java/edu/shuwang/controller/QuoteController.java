package edu.shuwang.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.shuwang.form.QuoteForm;
import edu.shuwang.model.Author;
import edu.shuwang.model.Quote;
import edu.shuwang.service.QuoteService;

@RestController
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;
    
    @Value("${service.author.name.uri}")
    private String authorServerNameUri;
    @Value("${service.author.id.uri}")
    private String authorServerIdUri;
    @Value("${service.author.save.uri}")
    private String authorServerSaveUri;
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
        System.out.println("QC random() is running");
        return quoteService.randomQuote();
    }
    
    @RequestMapping("/api/quote/list/{authorName}")
    public Quote[] list(@PathVariable("authorName") String authorName) {
        //pass the author name into the Controller use CRUD to solve the issue
        System.out.println("You are looking for quotes from " + authorName);
        
        RestTemplate restTemplate = new RestTemplate();
        String uri = authorServerNameUri + authorName;
        Author author = restTemplate.getForObject(uri, Author.class);
        
        return quoteService.findByAuthorId(author.getId());
    }
    
    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody QuoteForm quoteForm) {
    	RestTemplate restTemplate = new RestTemplate();
        String uri = authorServerNameUri+quoteForm.getAuthorName();
        Author a = restTemplate.getForObject(uri, Author.class);
        
        if (a == null) {
            System.out.println("----------Saving as a !!!NEW!!! author----------");
            System.out.println(quoteForm.getAuthorName());
            ResponseEntity<Long> st = restTemplate.postForEntity(authorServerSaveUri, quoteForm.getAuthorName(), Long.class);
            System.out.println(st.getBody());
            Quote quote = new Quote(quoteForm.getText(), quoteForm.getSource(), st.getBody().longValue());
            System.out.println("Saving Quote");
            quoteService.save(quote);
            return;
        } else {
            System.out.println("----------Saving as a !!!OLD!!! author----------");
            Quote quote = new Quote(quoteForm.getText(), quoteForm.getSource(), a.getId());
            System.out.println("Saving Quote");
            quoteService.save(quote);
            return;
        }
    }
    
    
//    public Quote fallback() {
//        Quote q = new Quote();
//        Author a = new Author("Confucius");
//        q.setText("The superior man is modest in his speech, but exceeds in his actions.");
//        q.setAuthor(a);
//        return q; 
//    }
}
