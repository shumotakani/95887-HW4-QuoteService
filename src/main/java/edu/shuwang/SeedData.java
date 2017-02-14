package edu.shuwang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import edu.shuwang.model.Author;
import edu.shuwang.model.Quote;
import edu.shuwang.SeedData;
import edu.shuwang.service.QuoteService;

@Configuration
public class SeedData {

    @Autowired
    private QuoteService quoteService;
    
    @Value("${service.author.uri}")
    private String authorServerUri;
    
    private static final Logger log = LoggerFactory.getLogger(SeedData.class);
    
    @Bean
    public SeedData getBean() {
        
    	RestTemplate restTemplate = new RestTemplate();
    	String uri1 = authorServerUri+"/" + 1;
    	String uri2 = authorServerUri+"/" + 2;
    	String uri3 = authorServerUri+"/" + 3;
    	
        Author a1 = restTemplate.getForObject(uri1, Author.class);
        Author a2 = restTemplate.getForObject(uri2, Author.class);
        Author a3 = restTemplate.getForObject(uri3, Author.class);
        
        Quote q1 = new Quote(
                "The world is a thing of utter inordinate complexity and richness " +
                "and strangeness that is absolutely awesome",
                "https://en.wikiquote.org/wiki/Douglas_Adams", 
                a1);
        
        Quote q2 = new Quote(
                "As rain breaks through an ill-thatched house, passion will break through an unreflecting mind.", 
                "https://en.wikiquote.org/wiki/Gautama_Buddha", 
                a2);
        
        Quote q3 = new Quote(
                "I think that only daring speculation can lead us further and not accumulation of facts.", 
                "https://en.wikiquote.org/wiki/Albert_Einstein", 
                a3);
        
        quoteService.save(q1);
        quoteService.save(q2);
        quoteService.save(q3);
        
        log.info("Quoates found with findAll():");
        log.info("-------------------------------");
        for (Quote m : quoteService.findAll()) {
            log.info(m.toString());
        }
        return new SeedData();
    }

}
