package classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import data.Corpus;
import data.CorpusLoaderException;
import data.TripAdvisorCorpusLoader;
import weka.NaiveBayesClassifier;
import weka.WekaFileGenerator;
import weka.WekaInterface;
import weka.core.Attribute;
import weka.core.Instances;
import models.Hotel;
import models.Review;

public class BinaryClassifier {
	private WekaInterface classifier;
	private String key;
	private Corpus corpus;
	private List<String> processedSentences;
	private List<String> processedResults;
	private static int LIMIT = 3;
	private String name;
	private Instances instances;
	
	
	public BinaryClassifier(){
		this.key = "";
		this.classifier = new NaiveBayesClassifier();
		this.name = "default";
	}
	public BinaryClassifier(String name){
		this.key = "";
		this.classifier = new NaiveBayesClassifier();
		this.name = name;
	}
	public void LoadCorpus(int limit) throws CorpusLoaderException{
		this.corpus = new TripAdvisorCorpusLoader().load();
		this.corpus.split(limit);
		System.out.println("Hoteles : " + corpus.size());
		System.out.println("Reviews : " + corpus.reviews());
	}
	public void LoadCorpus(Instances instances, String label) throws CorpusLoaderException{
		this.instances = instances;
		
	}
	public void processSentences(){
		this.processedSentences = new ArrayList<>();
		this.processedResults = new ArrayList<>();
		Iterator<Hotel> it = corpus.iterator();
		while(it.hasNext()){
			Hotel hotel = it.next();
			Iterator<Review> it2 = hotel.getReviews().iterator();
			while(it2.hasNext()){
				Review review = it2.next();
				this.extractFromReview(review);
				
			}
		}
	}
	private void extractFromReview(Review review) {
		review.setContent(review.getContent().replace("'", ""));
		this.processedSentences.add("'"+review.getContent()+"'");
		if(review.getOverall()>LIMIT) this.processedResults.add("GOOD");
		else this.processedResults.add("POOR");
		
	}
	
	public void launch() throws Exception{
		this.generateFile();
		this.classifier.loadData("data/"+this.name+".arff");
		this.classifier.filterData();
		this.classifier.trainClassifier();
		this.classifier.crossValidation("data/"+this.name+".arff");
	}
	public void launchInstances() throws Exception {
		this.classifier.loadData(this.instances);
		this.classifier.trainClassifier();
		this.classifier.crossValidation(this.instances);
		
	}
	private void generateFile() throws IOException {
		WekaFileGenerator wfg = new WekaFileGenerator("data/"+this.name+".arff");
		wfg.addAtribute("Sentence", "string");
		wfg.addAtribute("Result", "{GOOD,POOR}");
		wfg.generateAttributes();
		List<Object> values = new ArrayList<>();
		for(int i = 0; i < this.processedResults.size();i++){
			values = new ArrayList<>();
			values.add(this.processedSentences.get(i));
			values.add(this.processedResults.get(i));
			wfg.addLine(values);
			
		}
		wfg.close();
		
	}
	
	public void generateTest() throws IOException {
		WekaFileGenerator wfg = new WekaFileGenerator("data/"+this.name+"Test.arff");
		wfg.addAtribute("Sentence", "string");
		wfg.addAtribute("Result", "{GOOD,POOR}");
		wfg.generateAttributes();
		List<Object> values = new ArrayList<>();
		for(int i = 0; i < this.processedResults.size();i++){
			values = new ArrayList<>();
			values.add(this.processedSentences.get(i));
			values.add("?");
			wfg.addLine(values);
			
		}
		wfg.close();
		
	}
	public Instances classify(String string) throws Exception {
		// TODO Auto-generated method stub
		return this.classifier.labelData(string);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}
