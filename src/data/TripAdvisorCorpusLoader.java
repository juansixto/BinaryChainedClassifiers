package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import models.Author;
import models.Hotel;
import models.Review;


public class TripAdvisorCorpusLoader  implements CorpusLoader{
	private final static String CORPUS_PATH = "data/corpora/reviews";

	@Override
	public Corpus load() throws CorpusLoaderException {
		final Vector<Hotel> documents = new Vector<Hotel>();

		final File corpusPath = new File(CORPUS_PATH);
		
		if(corpusPath.isDirectory()) {
			final File[] documentFiles = corpusPath.listFiles();
			
			for(final File documentFile: documentFiles) {
				try {
					FileReader fr = new FileReader(documentFile.getPath());
					BufferedReader bf = new BufferedReader(fr);
					String cadena;
					boolean header = true;
					boolean reviews = false;
					String id = "";
					Hotel hotel = new Hotel();
					Review review = new Review();
					while ((cadena = bf.readLine())!=null) {
						
						if(header){
							if(cadena.contains("<Overall Rating>")){									
								cadena = cadena.replace("<Overall Rating>","");
								hotel.setOverallRating(Float.parseFloat(cadena));
								
							}
							if(cadena.contains("<Avg. Price>")){
								cadena = cadena.replace("<Avg. Price>","");
								if(cadena.contains("$")){
									cadena = cadena.replace("$","");
									cadena = cadena.replace(",",".");
									hotel.setAvgPrice(Float.parseFloat(cadena));
								}
							
							}
							if(cadena.contains("<URL>")){
								cadena = cadena.replace("<URL>","");
								hotel.setUrl(cadena);
								header = false;								
							}								
						} else{								
							if(cadena.length() == 0){
								reviews = true;
								review = new Review();
							}
							if(reviews){
								if (cadena.contains("<Author>")){
									cadena = cadena.replace("<Author>","");
									review.setAuthor(new Author(cadena));										
								}
								if (cadena.contains("<Content>")){
									cadena = cadena.replace("<Content>","");
									review.setContent(cadena);									
								}
								if (cadena.contains("<Date>")){
									cadena = cadena.replace("<Date>","");
									review.setDate(cadena);										
								}
								if (cadena.contains("<No. Reader>")){
									cadena = cadena.replace("<No. Reader>","");
									review.setReaders(Float.parseFloat(cadena));										
								}
								if (cadena.contains("<No. Helpful>")){
									cadena = cadena.replace("<No. Helpful>","");
									review.setHelpful(Float.parseFloat(cadena));										
								}
								if (cadena.contains("<Overall>")){
									cadena = cadena.replace("<Overall>","");
									review.setOverall(Float.parseFloat(cadena));									
								}
								if (cadena.contains("<Value>")){
									cadena = cadena.replace("<Value>","");
									review.setValue(Float.parseFloat(cadena));									
								}
								if (cadena.contains("<Rooms>")){
									cadena = cadena.replace("<Rooms>","");
									review.setRooms(Float.parseFloat(cadena));									
								}
								if (cadena.contains("<Location>")){
									cadena = cadena.replace("<Location>","");
									review.setLocation(Float.parseFloat(cadena));									
								}
								if (cadena.contains("<Cleanliness>")){
									cadena = cadena.replace("<Cleanliness>","");
									review.setCleanliness(Float.parseFloat(cadena));										
								}
								if (cadena.contains("<Check in / front desk>")){
									cadena = cadena.replace("<Check in / front desk>","");
									review.setCheckin(Float.parseFloat(cadena));										
								}
								if (cadena.contains("<Service>")){
									cadena = cadena.replace("<Service>","");
									review.setService(Float.parseFloat(cadena));										
								}
								if (cadena.contains("<Business service>")){
									cadena = cadena.replace("<Business service>","");
									review.setBussiness(Float.parseFloat(cadena));	
									hotel.setReview(review);
									review = new Review();
								}
							}
							
							} 
						}
					id = documentFile.getName();
					id = id.replace("hotel_", "");
					id = id.replace(".dat", "");
					hotel.setId(Integer.parseInt(id));
					
					documents.add(hotel);
					System.out.println(hotel.getName()+ "    " + hotel.getLocation());
					bf.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} else {
			throw new CorpusLoaderException("The corpus directory does not exist");
		}
		
		return new Corpus(documents); 
	}
	

}

