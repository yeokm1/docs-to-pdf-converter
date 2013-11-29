package com.yeokhengmeng.docstopdfconverter;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.docx4j.Docx4J;
import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DocToPDFConverter extends Converter {

	public DocToPDFConverter(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}


	@Override
	public void convert() throws Exception{

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
