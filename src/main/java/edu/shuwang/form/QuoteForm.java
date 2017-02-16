package edu.shuwang.form;

public class QuoteForm {
	private String text;
    private String source;
    private String authorName;
	public QuoteForm() {
	}
	public QuoteForm(String text, String source, String authorName) {
		this.text = text;
		this.source = source;
		this.authorName = authorName;
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
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	

}
