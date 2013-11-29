package com.yeokhengmeng.docstopdfconverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;


public class OdtToPDF extends Converter {

	public OdtToPDF(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}

	@Override
	public void convert() throws Exception {
		showLoadingMessage();       

		FileInputStream inStream = getInFileStream();
		OdfTextDocument document = OdfTextDocument.loadDocument(inStream);

		PdfOptions options = PdfOptions.create();
		FileOutputStream out = getOutFileStream();
		
		showProcessingMessage();
		PdfConverter.getInstance().convert(document, out, options);

		showFinishedMessage();


	}

}
