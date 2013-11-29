package com.yeokhengmeng.docstopdfconverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public abstract class Converter {

	
	private final String LOADING_FORMAT = "\nLoading file \"%1$s\"\n\n";
	private final String PROCESSING_FORMAT = "Load completed in %1$dms, now converting...\n\n";
	private final String SAVING_FORMAT = "Conversion to \"%1$s\" took %2$dms.\n\nTotal: %3$dms\n";
	
	private long startTime;
	private long startOfProcessTime;
	
	protected String inputFilePath;
	protected String outputFilePath;
	
	public Converter(String inputFilePath, String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}
	
	
	public abstract void convert() throws Exception;
	
	private void startTime(){
		startTime = System.currentTimeMillis();
		startOfProcessTime = startTime;
	}
	
	protected void showLoadingMessage(){
		System.out.format(LOADING_FORMAT, inputFilePath);
		startTime();
	}
	
	protected void showProcessingMessage(){
		long currentTime = System.currentTimeMillis();
		long prevProcessTook = currentTime - startOfProcessTime;
		
		System.out.format(PROCESSING_FORMAT, prevProcessTook);
		
		startOfProcessTime = System.currentTimeMillis();
		
	}
	
	protected void showFinishedMessage(){
		long currentTime = System.currentTimeMillis();
		long timeTaken = currentTime - startTime;
		long prevProcessTook = currentTime - startOfProcessTime;

		startOfProcessTime = System.currentTimeMillis();
		
		System.out.format(SAVING_FORMAT, outputFilePath, prevProcessTook, timeTaken);
	}
	
	protected FileInputStream getInFileStream() throws FileNotFoundException{
		File inFile = new File(inputFilePath);
		FileInputStream iStream = new FileInputStream(inFile);
		return iStream;
	}
	
	protected FileOutputStream getOutFileStream() throws IOException{
		File outFile = new File(outputFilePath);
		
		try{
			outFile.getParentFile().mkdirs();
		} catch (NullPointerException e){
			//Ignore error since it means not parent directories
		}
		
		outFile.createNewFile();
		FileOutputStream oStream = new FileOutputStream(outFile);
		return oStream;
	}
	
	
	
	
	
	
	
	
}
