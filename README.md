Docs to PDF Converter
=====================

A standalone Java command line tool that converts DOC, DOCX, PPT, PPTX and ODT documents to pdf files. (Requires JRE 7.)


<b>Why?</b><br>
I wanted a simple program that can convert Microsoft Office documents to PDF but without dependencies like LibreOffice or expensive proprietary solutions. Along the way, I decided to add ODT support as well. Seeing as how code to convert each individual format is scattered around the web, I decided to combine all those solutions into one single program.<br>

<b>Usage:</b>

java -jar doc-converter.jar -type "type" -input "path" -output "path" -verbose<br>
eg. <br>
java -jar doc-converter.jar -input test.doc<br>
java -jar doc-converter.jar -i test.ppt -o ~\output.pdf<br>
java -jar doc-converter.jar -i ~\no-extension-file -o ~\output.pdf -t docx<br>

<b>Parameters:</b><br>
-inputPath (-i, -in, -input) "path" : specifies a path for the input file<br>
 
-outputPath (-o, -out, -output) "path" : specifies a path for the output PDF, use input file directory and name.pdf if not specified (Optional)<br>

-type (-t) [DOC | DOCX | PPT | PPTX | ODT] : Specifies doc converter. Leave blank to let program infer via file  extension<br>

-verbose (-v) : To view intermediate processing messages. (Optional)<br>


<b>Caveats and technical details:</b><br>
This tool relies on Apache POI, xdocreport, docx4j and odfdom libraries. They are not 100% reliable and the output format may not always be what you desire.<br>


DOC:<br>
Generally ok but takes some time to convert.. I notice that after conversion, the paragraph spacing tends to increase affecting your page layout. Conversion is done using docx4j to convert DOC to DOCX then to PDF.<br>

DOCX:<br>
Very good results. Fast conversion too.  Conversion is done using xdocreport library.<br>

PPT and PPTX:<br>
Resulting file is a PDF comprising of a PNG embedded in each page. Should be good enough for printing. This is the limitation of the Apache POI and docx4j libraries.<br>

ODT:<br>
Quality and speed as good as DOCX. Conversion is done using odfdom of the Apache ODF Toolkit.<br>


<b>Main Libraries</b><br>
Apache POI:  https://poi.apache.org/<br>
xdocreport: http://code.google.com/p/xdocreport/<br>
docx4j: http://www.docx4java.org/<br>
odfdom: https://incubator.apache.org/odftoolkit/odfdom/<br>
and others...<br>
<br>
The MIT License (MIT)<br>
Copyright (c) 2013-2014 Yeo Kheng Meng<br>

