import java.io.FileInputStream;

import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DocToPDFConverter extends DocxToPDFConverter {

	public DocToPDFConverter(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}

	@Override
	protected WordprocessingMLPackage getMLPackage(FileInputStream iStream) throws Exception{
	    WordprocessingMLPackage mlPackage = Doc.convert(iStream);
		return mlPackage;
	}
	
	
}
