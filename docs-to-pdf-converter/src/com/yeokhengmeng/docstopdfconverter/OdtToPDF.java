package com.yeokhengmeng.docstopdfconverter;

import java.io.InputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;


public class OdtToPDF extends Converter {

	public OdtToPDF(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
	}


	@Override
	public void convert() throws Exception {
		loading();       


		OdfTextDocument document = OdfTextDocument.loadDocument(inStream);

		PdfOptions options = PdfOptions.create();

		
		processing();
		PdfConverter.getInstance().convert(document, outStream, options);

		finished();


	}

}
