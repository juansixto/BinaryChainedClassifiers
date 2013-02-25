import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import weka.core.Instances;

import classifier.BinaryClassifier;
import data.CorpusLoaderException;


public class test {

	@Test
	public void test() throws Exception {
		BinaryClassifier classifier = new BinaryClassifier("Prueba1");
		classifier.LoadCorpus(100);
		classifier.processSentences();
		classifier.launch();
		classifier.generateTest();
		Instances answer = classifier.classify("data/Prueba1Test.arff");
		
		BinaryClassifier classifier2 = new BinaryClassifier("Prueba2");
		classifier2.LoadCorpus(answer,"GOOD");		
		classifier2.launchInstances();
		answer = classifier2.classify("data/Prueba1Test.arff");
		BinaryClassifier classifierN;
		
		String name;
		int i = 0;
		while(true){
			name = "Prueba"+i++;
			classifierN = new BinaryClassifier(name);
			classifierN.LoadCorpus(answer,"GOOD");
			classifierN.launchInstances();
			answer = classifierN.classify("data/Prueba1Test.arff");
			
			
		}

		
		
	}

}
