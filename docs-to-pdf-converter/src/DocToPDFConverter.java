import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DocToPDFConverter extends DocxToPDFConverter {

	public DocToPDFConverter(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}

	@Override
	protected WordprocessingMLPackage getMLPackage(FileInputStream iStream) throws Exception{
		PrintStream originalStdout = System.out;
		
		//Disable stdout temporarily as Doc convert produces alot of output
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {
				//DO NOTHING
			}
		}));
		WordprocessingMLPackage mlPackage = Doc.convert(iStream);
		
		System.setOut(originalStdout);
		return mlPackage;
	}
	
	
}
