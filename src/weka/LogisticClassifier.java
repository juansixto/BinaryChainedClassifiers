package weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class LogisticClassifier implements WekaInterface{

	DataSource source;
	Instances inst;
	Logistic classifier;
	
	public LogisticClassifier(){
		
	}

	@Override
	public void loadData(String data) throws Exception {
		this.source = new DataSource(data);
		this.inst = source.getDataSet();
		 if (this.inst.classIndex() == -1)
			 this.inst.setClassIndex(this.inst.numAttributes() - 1);	
	}
	
	public void filterData() throws Exception{
		Instances data = source.getDataSet();
		StringToWordVector stv = new StringToWordVector();
		stv.setOptions(weka.core.Utils.splitOptions("-R first-last -W 1000 " +
				"-prune-rate -1.0 -N 0 " +
				"-stemmer weka.core.stemmers.NullStemmer -M 1 " +
				"-tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters  \\\" \\r\\n\\t.,;:\\\'\\\"()?!\""));

		stv.setInputFormat(data);
		Instances newdata = Filter.useFilter(data, stv);
		this.inst = newdata;
		this.inst.setClassIndex(0);	
		

	}

	@Override
	public void trainClassifier() throws Exception {
		   this.classifier = new Logistic();
		   this.classifier.buildClassifier(this.inst);
		    System.out.println(this.classifier);
		
	}
	@Override
	public void crossValidation(String traindata) throws Exception{
		 DataSource ds = new DataSource(traindata);
		 Instances instances = ds.getDataSet();
		 StringToWordVector stv = new StringToWordVector();
			stv.setOptions(weka.core.Utils.splitOptions("-R first-last -W 1000 " +
							"-prune-rate -1.0 -N 0 " +
							"-stemmer weka.core.stemmers.NullStemmer -M 1 " +
							"-tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters  \\\" \\r\\n\\t.,;:\\\'\\\"()?!\""));

		stv.setInputFormat(instances);
		instances = Filter.useFilter(instances, stv);
		 instances.setClassIndex(0);
		 Evaluation eval = new Evaluation(instances);
		 eval.crossValidateModel(this.classifier, instances, 10, new Random(1));
		 System.out.println(eval.toSummaryString());
		 System.out.println(eval.toMatrixString());
	}
	@Override
	public Instances labelData(String data) throws Exception {
		Instances unlabeled = new Instances(new BufferedReader(new FileReader(data)));
			// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		// create copy
		Instances labeled = new Instances(unlabeled);
	    for (int i = 0; i < unlabeled.numInstances(); i++) {
		    Instance ui = unlabeled.instance(i);
		    double clsLabel = this.classifier.classifyInstance(ui);
		    labeled.instance(i).setClassValue(clsLabel);  
		    System.out.println(ui.toString() + " -> " + unlabeled.classAttribute().value((int) clsLabel));
	    }
	    return labeled;
	}
}
