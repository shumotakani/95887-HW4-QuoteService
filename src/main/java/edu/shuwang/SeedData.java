package edu.shuwang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.shuwang.SeedData;
import edu.shuwang.service.QuoteService;

public class SeedData {

	public SeedData() {
		@Autowired
	    private AuthorService authorService;
	    
	    @Autowired
	    private QuoteService quoteService;
	    
	    private static final Logger log = LoggerFactory.getLogger(SeedData.class);
	}

}
