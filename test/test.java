import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import classifier.BinaryClassifier;
import data.CorpusLoaderException;


public class test {

	@Test
	public void test() throws Exception {
		BinaryClassifier classifier = new BinaryClassifier("Prueba1");
		classifier.LoadCorpus(20);
		classifier.processSentences();
		classifier.launch();
		
		
	}

}
