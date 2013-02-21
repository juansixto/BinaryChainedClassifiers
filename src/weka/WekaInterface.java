package weka;

import weka.core.Instances;

public interface WekaInterface {
	
	void loadData(String data) throws Exception;
	void trainClassifier() throws Exception;
	void crossValidation(String traindata) throws Exception;
	Instances labelData(String data) throws Exception;
	void filterData() throws Exception;
	

}
