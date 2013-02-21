package weka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WekaFileGenerator {
	
	private String  rFile;
	private File  file;
	private PrintWriter  bufferw;
	private List<Attribute> attribs;

	public WekaFileGenerator(String file) throws IOException{
		this.rFile = file;
		this.attribs = new ArrayList<>();
		this.file = new File(rFile);
		this.bufferw = new PrintWriter (new FileWriter (this.file));
	}

	public List<Attribute> getAttribs() {
		return attribs;
	}

	public void setAttribs(List<Attribute> attribs) {
		this.attribs = attribs;
	}
	public void close()
	{
		this.bufferw.close();
	}
	
	public void addAtribute(String name, String type){
		this.attribs.add(new Attribute(name, type));
		
	}
	public void generateAttributes(){
		Attribute at;
		this.bufferw.println("@relation WekaDemo");
		this.bufferw.println("");
		Iterator<Attribute> iterator = this.attribs.iterator();
		while(iterator.hasNext()){
			at = iterator.next();
			this.bufferw.println("@attribute "+at.name+ " "+ at.type);
		}
		this.bufferw.println("");
		this.bufferw.println("@data");
	}
	public void addLine(List<Object> values){
		Iterator<Object> it = values.iterator();
		String line = "";
		while(it.hasNext()){
			line += it.next().toString() + ",";
		}
		this.bufferw.println(line);
	}
	
	private class Attribute {
		private String name;
		private String type;
		
		private Attribute(String name, String type){
			this.name = name;
			this.type = type;
		}
	}
}
