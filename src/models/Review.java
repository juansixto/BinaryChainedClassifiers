package models;

public class Review {
	private Author author;
	private String content;
	private String date;
	private float readers;
	private float helpful;
	private float overall;
	private float value;
	private float rooms;
	private float location;
	private float cleanliness;
	private float checkin;
	private float service;
	private float bussiness;
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String cadena) {
		this.date = cadena;
	}
	public float getReaders() {
		return readers;
	}
	public void setReaders(float f) {
		this.readers = f;
	}
	public float getHelpful() {
		return helpful;
	}
	public void setHelpful(float helpful) {
		this.helpful = helpful;
	}
	public float getOverall() {
		return overall;
	}
	public void setOverall(float overall) {
		this.overall = overall;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getRooms() {
		return rooms;
	}
	public void setRooms(float rooms) {
		this.rooms = rooms;
	}
	public float getLocation() {
		return location;
	}
	public void setLocation(float location) {
		this.location = location;
	}
	public float getCleanliness() {
		return cleanliness;
	}
	public void setCleanliness(float cleanliness) {
		this.cleanliness = cleanliness;
	}
	public float getCheckin() {
		return checkin;
	}
	public void setCheckin(float checkin) {
		this.checkin = checkin;
	}
	public float getService() {
		return service;
	}
	public void setService(float service) {
		this.service = service;
	}
	public float getBussiness() {
		return bussiness;
	}
	public void setBussiness(float bussiness) {
		this.bussiness = bussiness;
	}
	public float getKeyValue(String key){
		if(key.contains("Value")){
			return this.getValue();
		}else if(key.contains("Rooms")){
			return this.getRooms();
		}else if(key.contains("Location")){
			return this.getLocation();
		}else if(key.contains("Cleanliness")){
			System.out.println("Puntuando Cleanliness");
			return this.getCleanliness();
		}else if(key.contains("Check_in/front_desk")){
			return this.getCheckin();
		}else if(key.contains("Service")){
			return this.getService();
		}else if(key.contains("Business_service")){
			return this.getBussiness();
		}else{
			return this.getOverall();
		}
	}
	public void print() {
		System.out.println("Author: " + this.author.getName());
		System.out.println("Content: " + this.content);
		System.out.println("Date:" + this.date);
		System.out.println("Readers: " + this.readers);
		System.out.println("Helpful: "+ this.helpful);
		System.out.println("Overall: "+ this.overall);
		System.out.println("Value: " + this.value);
		System.out.println("Rooms: "+ this.rooms);
		System.out.println("Location: "+ this.location);
		System.out.println("Clearliness: " + this.cleanliness);
		System.out.println("Checkin: " + this.checkin);
		System.out.println("Service: " + this.service);
		System.out.println("Bussiness: " + this.bussiness);
		
	}

	
	

}
