package edu.shuwang.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import edu.shuwang.model.Quote;


public class QuoteServiceImpl implements QuoteServiceCustom {

    Random random = new Random();
    
    @Autowired
    private QuoteService quoteService;
    
    @Override
    public Quote randomQuote() {
        ArrayList<Quote> quotes = new ArrayList<Quote>();
        for (Quote q: this.quoteService.findAll() ) {
            quotes.add(q);
        }
        System.out.println(quotes);
        Quote q = quotes.get(random.nextInt(quotes.size()));
        return q;
    }

}
