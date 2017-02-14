package edu.shuwang.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String text;
    private String source;
    
    
    @ManyToOne(optional = false)
    private Author author;
    

    public Quote() {}

    public Quote(String text, String source, Author author) {
        this.text = text;
        this.source = source;
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("Quote[id=%d, text='%s', by='%s']", this.id, this.text, this.author.getName());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }
    
    
}
