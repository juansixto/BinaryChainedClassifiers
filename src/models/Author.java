package models;

public class Author {

	private String name;

	public Author(String cadena) {
		this.name = cadena;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
