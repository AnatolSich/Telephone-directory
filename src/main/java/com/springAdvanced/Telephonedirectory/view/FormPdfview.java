package com.springAdvanced.Telephonedirectory.view;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.springAdvanced.Telephonedirectory.service.FreemarkerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.StringReader;
import java.net.URL;
import java.util.Map;

public class FormPdfview extends AbstractITextPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        URL fileResource = FormPdfview.class.getResource("/templates");
        String html = FreemarkerUtils.loadFtlHtml(new File(fileResource.getFile()), "simpleForm.ftlh", model);

        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
        //XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()), Charset.forName("UTF-8"), new AsianFontProvider() );
    }
}
