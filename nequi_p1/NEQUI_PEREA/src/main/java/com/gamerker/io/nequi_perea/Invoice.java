/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamerker.io.nequi_perea;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 *
 * @author hp
 */
public class Invoice {
    private final String invoiceId;
    private final NequiAccount account;
    private final HistoryItem historyItem;

    public Invoice(NequiAccount account, HistoryItem historyItem) {
        if (account == null) throw new IllegalArgumentException("account cannot be null");
        if (historyItem == null) throw new IllegalArgumentException("historyItem cannot be null");
        this.account = account;
        this.historyItem = historyItem;
        this.invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Generates the PDF invoice in the current working directory and returns the
     * generated file name (or null on error).
     */
    public String generatePdf() {
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        String fileName = "invoices/invoice_" + invoiceId + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Optional logo (place a "nequi_logo.png" in the working directory to include it)
            try {
                File logo = new File("nequi_logo.png");
                if (logo.exists()) {
                    Image img = Image.getInstance(logo.getAbsolutePath());
                    img.scaleToFit(100f, 100f);
                    img.setAlignment(Element.ALIGN_LEFT);
                    document.add(img);
                }
            } catch (Exception ignore) {
                // If logo can't be added, we continue without failing the whole invoice.
            }

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Factura - Nequi by PEREA", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Meta information (company / invoice)
            PdfPTable meta = new PdfPTable(2);
            meta.setWidthPercentage(100);
            meta.setWidths(new float[] {2f, 1f});

            PdfPCell left = new PdfPCell();
            left.setBorder(Rectangle.NO_BORDER);
            left.addElement(new Paragraph("Nequi by Perea"));
            left.addElement(new Paragraph("NIT: 123098890-0"));
            left.addElement(new Paragraph("soporte@nequi-perea.com"));
            left.addElement(new Paragraph("Tel: +57 301 1111 1111"));
            meta.addCell(left);

            PdfPCell right = new PdfPCell();
            right.setBorder(Rectangle.NO_BORDER);
            right.addElement(new Paragraph("Factura ID: " + invoiceId));
            right.addElement(new Paragraph("Generada: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            right.addElement(new Paragraph("Operación (fecha): " + historyItem.getDate()));
            meta.addCell(right);

            document.add(meta);
            document.add(Chunk.NEWLINE);

            // Client information
            PdfPTable client = new PdfPTable(2);
            client.setWidthPercentage(100);
            client.setWidths(new float[] {1f, 2f});

            User u = account.getUsuario();
            client.addCell(cellNoBorder("Cliente:"));
            client.addCell(cellNoBorder(u != null ? u.getFullname() : "Usuario no registrado"));
            client.addCell(cellNoBorder("Documento:"));
            client.addCell(cellNoBorder(u != null ? u.getDocument() : "-"));
            client.addCell(cellNoBorder("Celular:"));
            client.addCell(cellNoBorder(u != null ? u.getCellphoneNumber() : "-"));
            client.addCell(cellNoBorder("Email:"));
            client.addCell(cellNoBorder(u != null ? u.getEmail() : "-"));

            document.add(client);
            document.add(Chunk.NEWLINE);

            // Items table (operation as a single item)
            PdfPTable items = new PdfPTable(3);
            items.setWidthPercentage(100);
            items.setWidths(new float[] {1f, 6f, 2f});

            items.addCell(headerCell("No"));
            items.addCell(headerCell("Descripción"));
            items.addCell(headerCell("Valor"));

            NequiOperation op = historyItem.getOperationItem();
            items.addCell(cellNoBorder("1"));
            items.addCell(cellNoBorder(op.getDescription()));
            items.addCell(cellNoBorder(formatCurrency(op.getAmount())));

            document.add(items);
            document.add(Chunk.NEWLINE);

            // Totals
            PdfPTable totals = new PdfPTable(2);
            totals.setWidthPercentage(40);
            totals.setHorizontalAlignment(Element.ALIGN_RIGHT);

            totals.addCell(cellNoBorder("Subtotal:"));
            totals.addCell(cellNoBorder(formatCurrency(op.getAmount())));
            // If you later add taxes/fees, calculate and add here
            totals.addCell(cellNoBorder("Total:"));
            totals.addCell(cellNoBorder(formatCurrency(op.getAmount())));

            document.add(totals);

            // Footer
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Gracias por usar Nequi by Perea",
                    FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            if (document.isOpen()) document.close();
            return null;
        }
    }

    // Small helpers for table cells
    private PdfPCell cellNoBorder(String text) {
        PdfPCell c = new PdfPCell(new Phrase(text));
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(4f);
        return c;
    }

    private PdfPCell headerCell(String text) {
        PdfPCell c = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setPadding(6f);
        return c;
    }

    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}

