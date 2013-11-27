Docs to PDF Converter
=====================

A standalone Java command line tool that converts DOC, DOCX, PPT and PPTX documents to pdf files. (Requires JRE 7.)


<b>Why?</b><br>
I wanted a simple program that can convert Microsoft Office documents to PDF but without dependencies like LibreOffice or expensive proprietary solutions.<br>

<b>Usage:</b>

java -jar doc-converter.jar -type "type" -inputPath "path" -outputPath "path" -verbose<br>
eg. <br>
java -jar doc-converter.jar -inputPath test.doc<br>
java -jar doc-converter.jar -i test.ppt -o ~\output.pdf<br>
java -jar doc-converter.jar -i ~\no-extension-file -o ~\output.pdf -t docx<br>

<b>Parameters:</b><br>
-inputPath (-i, -in, -input) "path"    : specifies a path for the input file<br>
 
-outputPath (-o, -out, -output) "path" : specifies a path for the output PDF, use input file directory and name.pdf if not specified (Optional)<br>

-type (-t) [doc | docx | ppt | pptx]   : Specifies doc converter. Leave blank to let program infer by input extension (Optional)<br>

-verbose (-v)                          : To view intermediate processing messages. (Optional)<br>


<b>Caveats:</b><br>
This tool relies on Apache POI and docx4j libraries. They are not 100% reliable and the output format may not always be what you desire.<br>


DOC and DOCX:<br>
Generally ok.<br>

PPT and PPTX<br>
Resulting file is a PDF comprising of PNG images in each page. This is the limitation of the Apache POI and docx4j libraries.<br>


<b>Main Libraries</b><br>
Apache POI:  https://poi.apache.org/<br>
docx4j: http://www.docx4java.org/<br>
and others...<br>
<br>
The MIT License (MIT)<br>
Copyright (c) 2013-2014 Yeo Kheng Meng<br>

