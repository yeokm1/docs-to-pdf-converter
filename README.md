Docs to PDF Converter
=====================

**(I'm not maintaining this code as I neither have personal resources nor am I still using this project. I'll be happy to oblige if you have any pull requests or even if you wish to be a co-maintainer.)**

A standalone Java library/command line tool that converts DOC, DOCX, PPT, PPTX and ODT documents to pdf files. (Requires JRE 7)

The v1.7 release has not been updated for about 2 years although it seems quite reliable for me. In response to an [issue request](https://github.com/yeokm1/docs-to-pdf-converter/issues/1) to update the libraries, I have done so with the new v1.8. I now use Maven to managed the libraries in the pom.xml file.

I have not tested v1.8 much so if you face any issues, you can still use v1.7 in the Releases section.

# Table of content

  + [Why?](#why)
  + [Command Line Usage](#command-line-usage)
  + [Parameters](#parameters)
  + [Library Usage](#library-usage)
  + [Caveats and technical details](#caveats-and-technical-details)
    - [DOC](#doc)
    - [DOCX](#docx)
    - [PPT and PPTX](#ppt-and-pptx)
    - [ODT](#odt)
  + [Main Libraries](#main-libraries)
* [Compiling the code](#compiling-the-code)

### Why?  
I wanted a simple program that can convert Microsoft Office documents to PDF but without dependencies like LibreOffice or expensive proprietary solutions. Seeing as how code and libraries to convert each individual format is scattered around the web, I decided to combine all those solutions into one single program. Along the way, I decided to add ODT support as well since I encountered the code too.

### Command Line Usage:  

```
java -jar doc-converter.jar -type "type" -input "path" -output "path" -verbose
java -jar doc-converter.jar -input test.doc
java -jar doc-converter.jar -i test.ppt -o ~\output.pdf
java -jar doc-converter.jar -i ~\no-extension-file -o ~\output.pdf -t docx
```

### Parameters:  
```
-inputPath (-i, -in, -input) "path" : specifies a path for the input file

-outputPath (-o, -out, -output) "path" : specifies a path for the output PDF, use input file directory and name.pdf if not specified (Optional)

-type (-t) [DOC | DOCX | PPT | PPTX | ODT] : Specifies doc converter. Leave blank to let program infer via file  extension (Optional)

-verbose (-v) : To view intermediate processing messages. (Optional)
```

### Library Usage:  

1. Drop the jar into your lib folder and add to build path.  
2. Choose the converter of your choice, they are named DocToPDFConverter, DocxToPDFConverter, PptToPDFConverter, PptxToPDFConverter and OdtToPDFConverter.  
3. Instantiate with 4 parameters  
   - InputStream `inStream`: Document source stream to be converted  
   - OutputStream `outStream`: Document output stream  
   - boolean `showMessages`: Whether to show intermediate processing messages to Standard Out (stdout)  
   - boolean `closeStreamsWhenComplete`: Whether to close input and output streams when complete  
4. Call the "convert()" method and wait.  


### Caveats and technical details:  
This tool relies on Apache POI, xdocreport, docx4j and odfdom libraries. They are not 100% reliable and the output format may not always be what you desire.


#### DOC:
Generally ok but takes some time to convert.. I notice that after conversion, the paragraph spacing tends to increase affecting your page layout. Conversion is done using docx4j to convert DOC to DOCX then to PDF.(Cannot use xdocreport once the DOCX data is obtained as the intermediate data structure is docx4j specific.)

#### DOCX:
Very good results. Fast conversion too.  Conversion is done using xdocreport library as it seems faster and more accurate than docx4j.

#### PPT and PPTX:
Resulting file is a PDF comprising of a PNG embedded in each page. Should be good enough for printing. This is the limitation of the Apache POI and docx4j libraries.

#### ODT:
Quality and speed as good as DOCX. Conversion is done using odfdom of the Apache ODF Toolkit.

### Main Libraries  
Apache POI:  https://poi.apache.org/  
xdocreport: http://code.google.com/p/xdocreport/  
docx4j: http://www.docx4java.org/  
odfdom: https://incubator.apache.org/odftoolkit/odfdom/  
and others...  

## Compiling the code

### I just want the jar

```bash
# If you don't already have Maven in your Mac
brew install maven
mvn clean package
```

The output jar file can be found in the `target` folder.

### Development
I'm using Eclipse Mars IDE Java EE with the M2Eclipse plugin. Simply create a workspace and import my project into it. Let Maven do its work in downloading all the necessary dependencies. Once everything is downloaded, you should be able to run the MainClass.

More details can be found in the [Wiki section](https://github.com/yeokm1/docs-to-pdf-converter/wiki/Setting-up-your-IDE-to-compile-the-project).

The MIT License (MIT)
Copyright (c) 2013-2014 Yeo Kheng Meng
