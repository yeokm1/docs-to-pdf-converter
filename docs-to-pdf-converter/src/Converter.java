import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public abstract class Converter {

	
	private final String LOADING_FORMAT = "Loading file from %1$s\n";
	private final String PROCESSING_FORMAT = "Processing %1$s\n";
	private final String SAVING_FORMAT = "Saved to %1$s, conversion took %2$dms\n";
	
	private long startTime;
	
	protected String inputFilePath;
	protected String outputFilePath;
	
	public Converter(String inputFilePath, String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}
	
	
	public abstract void convert() throws Exception;
	
	protected void startTime(){
		startTime = System.currentTimeMillis();
	}
	
	protected void showLoadingMessage(){
		System.out.format(LOADING_FORMAT, inputFilePath);
	}
	
	protected void showProcessingMessage(){
		System.out.format(PROCESSING_FORMAT, inputFilePath);
	}
	
	protected void showFinishedMessage(){
		long timeTaken = System.currentTimeMillis() - startTime;
		System.out.format(SAVING_FORMAT, outputFilePath, timeTaken);
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
