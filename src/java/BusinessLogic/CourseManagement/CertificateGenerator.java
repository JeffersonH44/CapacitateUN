/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.CourseManagement;

import BusinessLogic.UserManagement.PrivilegeVerifier;
import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.text.DateFormat;


/**
 * Clase encargada de la generación de certificados.
 * @author manu
 */
public class CertificateGenerator implements Serializable {
    
    /**
     * 
     * @param course
     * @return verdadero en caso de que el documento se haya generado exitosamente.
     * @throws FileNotFoundException - en caso de no encontrar el archivo pdf
     * @throws DocumentException - en caso de que en la generación del documento haya un error.
     */
    public boolean createPDF(Courses course) throws FileNotFoundException, DocumentException
    {
        try{
            User user = new PrivilegeVerifier().getUserLogged();
            
            Document document=new Document();
            File file = new File("C:\\proyecto\\CapacitateUN\\web\\pages\\user\\certificate.pdf");
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            
            Font font = new Font(Font.FontFamily.COURIER);
            
            document.addTitle("certificado de inscripción");
            font.setStyle(Font.ITALIC);
            Chunk chunk = new Chunk(DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
            chunk.setFont(font);
            document.add(chunk);
            
            //Chunk chunk = new Chunk("iText Test");
            //font.setStyle(Font.BOLDITALIC);
            //chunk.setFont(font);
            //chunk.setBackground(BaseColor.LIGHT_GRAY);
            //document.add(chunk);

            Paragraph paragraph = new Paragraph();
            font.setStyle(Font.BOLDITALIC);
            font.setSize(16);
            paragraph.setFont(font);
            paragraph.add("\n\n\n\n\n\ncertificado de inscripción\n\n\n\n\n\n\n");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            paragraph = new Paragraph();
            font.setFamily("Helvetica");
            font.setSize(12);
            font.setStyle(Font.NORMAL);
            paragraph.setFont(font);
            paragraph.add("El sistema CapacitateUN certifica que ");
            paragraph.add( user.getFirstname()+ " " + user.getLastname());
            paragraph.add(" identificado con cedula "+ user.getId());
            paragraph.add(" se encuentra registrado en el curso ");
            if (course != null){
                paragraph.add(course.getName());
            }else{
                paragraph.add("curso no encontrado");
            }
            paragraph.add(" al momento de expedición de este certificado.");
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph);
            document.close();
            return true;
        }catch (FileNotFoundException | DocumentException e) {
                throw e;
        }
        //return false;
    }
}
