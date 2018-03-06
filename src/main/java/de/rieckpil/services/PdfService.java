package de.rieckpil.services;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.itextpdf.text.DocumentException;
import lombok.SneakyThrows;

@Service
public class PdfService {
  
  private TemplateEngine templateEngine;

  public PdfService(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public void createSamplePdf() throws IOException, DocumentException {
     
    Context context = new Context();
    context.setVariable("name", "Thomas");
     
    // Get the plain HTML with the resolved ${name} variable!
    String html = templateEngine.process("pdfs/invoice", context);
    
    String xHtml = convertToXhtml(html);

    ITextRenderer renderer = new ITextRenderer();
    renderer.getFontResolver().addFont("Raleway-Regular.ttf", IDENTITY_H, EMBEDDED);
    renderer.setDocumentFromString(xHtml);
    renderer.layout();
    
    OutputStream outputStream = new FileOutputStream("/Users/Philip/Desktop/pdfBoxHelloWorld.pdf");
    renderer.createPDF(outputStream);
    outputStream.close();
  }

  @SneakyThrows
  private String convertToXhtml(String html) {
    Tidy tidy = new Tidy();
    tidy.setInputEncoding("UTF-8");
    tidy.setOutputEncoding("UTF-8");
    tidy.setXHTML(true);
    ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    tidy.parseDOM(inputStream, outputStream);
    return outputStream.toString("UTF-8");
  }

}
