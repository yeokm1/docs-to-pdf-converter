package com.yeokhengmeng.docstopdfconverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxToPDFConverter extends Converter {

	public DocxToPDFConverter(String inputFilePath, String outputFilePath) {
		super(inputFilePath, outputFilePath);
	}

	@Override
	public void convert() throws Exception {
		startTime();
		showLoadingMessage();       
		
		FileInputStream is = getInFileStream();
        XWPFDocument document = new XWPFDocument(is);

        PdfOptions options = PdfOptions.create();
        FileOutputStream out = getOutFileStream();
        
        showProcessingMessage();
        PdfConverter.getInstance().convert(document, out, options);
        
        showFinishedMessage();
        
	}

}
