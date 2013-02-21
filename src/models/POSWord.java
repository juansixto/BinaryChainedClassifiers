package models;

public class POSWord {
	private String word;
	private String pos;
	private int position;
	
	public POSWord(String word, String pos){
		this.word = word;
		this.pos = pos;
	}
	public POSWord(String word, String pos, int position){
		this.word = word;
		this.pos = pos;
		this.position = position;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}

}
