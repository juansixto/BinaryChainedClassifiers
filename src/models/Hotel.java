package models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

	private String name;
	private String location;
	private int id;
	private float overallRating;
	private float avgPrice;
	private String url;
	private List<Review> reviews;
	public Hotel() {
		this.reviews = new ArrayList<>();
		this.name = "Default";
		this.id = 0;
		this.overallRating = (float) 99.99 ;
		this.avgPrice = (float) 99.99;
		this.url = "";
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.replace("_", " ");
	}
	public float getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(float f) {
		this.overallRating = f;
	}
	public float getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(float avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
		String name = url.replace(".html", "");
		String[] ns = name.split("-");
		this.setName(ns[4]);
		this.setLocation(ns[5]);
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public Review getReview(int index) {
		return reviews.get(index);
	}
	public void setReview(Review review) {
		this.reviews.add(review);
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location.replace("_", " ");
	}
	public void print() {
		System.out.println("Hotel: "+this.name);
		System.out.println("Overall Rating: "+ this.overallRating);
		System.out.println("Value Aspect Rating: "+ this.avgPrice);
		System.out.println("URL: "+this.url);
		System.out.println("Reviews:");
		for(int i=0; i<this.reviews.size();i++){
			this.reviews.get(i).print();
			System.out.println();
		}
	}
}
