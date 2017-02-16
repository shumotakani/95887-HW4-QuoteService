 package edu.shuwang.model;

public class Author {
    private Long id;
    private String name;
    

    protected Author() {}

    public Author(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Author[id=%d, name='%s']", id, this.name);
    }
    
    public Long getId () {
        return this.id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
