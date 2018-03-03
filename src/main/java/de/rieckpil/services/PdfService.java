package de.rieckpil.services;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

  public void createSamplePdf() throws IOException {
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);

    PDPageContentStream contentStream = new PDPageContentStream(document, page);

    contentStream.setFont(PDType1Font.COURIER, 12);
    contentStream.beginText();
    contentStream.showText("Hello World");
    contentStream.endText();
    contentStream.close();
    
    document.save("/Users/Philip/Desktop/pdfBoxHelloWorld.pdf");
    document.close();
  }

}
