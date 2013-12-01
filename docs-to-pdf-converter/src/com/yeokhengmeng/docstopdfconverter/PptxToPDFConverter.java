package com.yeokhengmeng.docstopdfconverter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;


public class PptxToPDFConverter extends Converter{

	

	public PptxToPDFConverter(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
	}


	private XSLFSlide[] slides;
	

	@Override
	public void convert() throws Exception {
		loading();
		


		Dimension pgsize = processSlides();
		
		processing();
		
	    double zoom = 2; // magnify it by 2 as typical slides are low res
	    AffineTransform at = new AffineTransform();
	    at.setToScale(zoom, zoom);

		
		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, outStream);
		document.open();
		
		for (int i = 0; i < getNumSlides(); i++) {

			BufferedImage bufImg = new BufferedImage((int)Math.ceil(pgsize.width*zoom), (int)Math.ceil(pgsize.height*zoom), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = bufImg.createGraphics();
			graphics.setTransform(at);
			//clear the drawing area
			graphics.setPaint(getSlideBGColor(i));
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
			try{
				drawOntoThisGraphic(i, graphics);
			} catch(Exception e){
				//Just ignore, draw what I have
			}
			
			Image image = Image.getInstance(bufImg, null);
			document.setPageSize(new Rectangle(image.getScaledWidth(), image.getScaledHeight()));
			document.newPage();
			image.setAbsolutePosition(0, 0);
			document.add(image);
		}
		//Seems like I must close document if not output stream is not complete
		document.close();
		
		//Not sure what repercussions are there for closing a writer but just do it.
		writer.close();
		finished();
		

	}
	
	protected Dimension processSlides() throws IOException{
		InputStream iStream = inStream;
		XMLSlideShow ppt = new XMLSlideShow(iStream);
		Dimension dimension = ppt.getPageSize();
		slides = ppt.getSlides();
		return dimension;
	}
	
	protected int getNumSlides(){
		return slides.length;
	}
	
	
	protected void drawOntoThisGraphic(int index, Graphics2D graphics){
		slides[index].draw(graphics);
	}
	
	protected Color getSlideBGColor(int index){
		return slides[index].getBackground().getFillColor();
	}
	
	
	
	




}
