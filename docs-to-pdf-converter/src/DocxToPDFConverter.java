import java.io.FileInputStream;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DocxToPDFConverter extends Converter {

	public DocxToPDFConverter(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}


	@Override
	public void convert() throws Exception{
		startTime();

		showLoadingMessage();

		FileInputStream iStream = getInFileStream();


		WordprocessingMLPackage wordMLPackage = getMLPackage(iStream);

		iStream.close();

		OutputStream os = getOutFileStream();

		showProcessingMessage();
		Docx4J.toPDF(wordMLPackage, os);
		os.close();

		showFinishedMessage();

	}

	protected WordprocessingMLPackage getMLPackage(FileInputStream iStream) throws Exception{
		return WordprocessingMLPackage.load(iStream);
	}

}
