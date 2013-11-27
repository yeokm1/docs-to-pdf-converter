import java.io.OutputStream;
import java.io.PrintStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;



public class MainClass{

	public enum DOC_TYPE {
		DOC,
		DOCX,
		PPT,
		PPTX
	}


	public static void main(String[] args){
		Converter converter = processArguments(args);


		if(converter == null){
			System.out.println("Unable to determine type of input file");
		} else {
			try {
				converter.convert();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	public static Converter processArguments(String[] args){
		CommandLineValues values = new CommandLineValues();
		CmdLineParser parser = new CmdLineParser(values);

		Converter converter = null;
		try {
			parser.parseArgument(args);

			String inPath = values.inFilePath;
			String outPath = values.outFilePath;
			boolean shouldShowMessages = values.verbose;

			if(outPath == null){
				outPath = changeExtensionToPDF(inPath);
			}

			if(!shouldShowMessages){
				System.setOut(new PrintStream(new OutputStream() {
					public void write(int b) {
						//DO NOTHING
					}
				}));
			}

			if(values.type == null){
				if(inPath.endsWith("doc")){
					converter = new DocToPDFConverter(inPath, outPath);
				} else if (inPath.endsWith("docx")){
					converter = new DocxToPDFConverter(inPath, outPath);
				} else if(inPath.endsWith("ppt")){
					converter = new PptToPDFConverter(inPath, outPath);
				} else if(inPath.endsWith("pptx")){
					converter = new PptxToPDFConverter(inPath, outPath);
				} else {
					converter = null;
				}


			} else {

				switch(values.type){
				case DOC: converter = new DocToPDFConverter(inPath, outPath);
				break; 
				case DOCX: converter = new DocxToPDFConverter(inPath, outPath);
				break;
				case PPT:  converter = new PptToPDFConverter(inPath, outPath);
				break;
				case PPTX: converter = new PptxToPDFConverter(inPath, outPath);
				break;
				default: converter = null;
				break;

				}


			}

		} catch (CmdLineException e) {
			// handling of wrong arguments
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
		}

		return converter;

	}


	public static class CommandLineValues {

		@Option(name = "-type", aliases = "-t", required = false, usage = "Specifies doc converter. Leave blank to let program decide by input extension.")
		public DOC_TYPE type = null;


		@Option(name = "-inputPath", aliases = {"-i", "-in", "-input"}, required = true,  metaVar = "<path>",
				usage = "Specifies a path for the input file.")
		public String inFilePath = null;


		@Option(name = "-outputPath", aliases = {"-o", "-out", "-output"}, required = false, metaVar = "<path>",
				usage = "Specifies a path for the output PDF.")
		public String outFilePath = null;

		@Option(name = "-verbose", aliases = {"-v"}, required = false, usage = "To see intermediate processing messages.")
		public boolean verbose = false;


	}

	//From http://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java
	public static String changeExtensionToPDF(String originalPath) {

		String separator = System.getProperty("file.separator");
		String filename;

		// Remove the path upto the filename.
		int lastSeparatorIndex = originalPath.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = originalPath;
		} else {
			filename = originalPath.substring(lastSeparatorIndex + 1);
		}

		// Remove the extension.
		int extensionIndex = filename.lastIndexOf(".");

		String removedExtension;
		if (extensionIndex == -1){
			removedExtension =  filename;
		} else {
			removedExtension =  filename.substring(0, extensionIndex);
		}
		String addPDFExtension = removedExtension + ".pdf";

		return addPDFExtension;
	}











}
