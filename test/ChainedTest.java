import static org.junit.Assert.*;

import org.junit.Test;

import weka.core.Instances;
import classifier.BinaryClassifier;
import data.CorpusLoaderException;


public class ChainedTest {

	@Test
	public void test() throws Exception {
		BinaryClassifier classifier = new BinaryClassifier("Prueba1");
		classifier.LoadCorpus(100);
		classifier.processSentences();
		classifier.launch();
		classifier.generateTest();
		Instances answer = classifier.classify("data/Prueba1Test.arff");
	}

}
