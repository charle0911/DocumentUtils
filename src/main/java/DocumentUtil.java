import org.apache.poi.xwpf.usermodel.*;

import java.util.List;

public class DocumentUtil {

    public static XWPFDocument replacePOI(XWPFDocument doc, String placeHolder, String replaceText) {
        // REPLACE ALL HEADERS
        for (XWPFHeader header : doc.getHeaderList()) {
            replaceAllBodyElements(header.getBodyElements(), placeHolder, replaceText);
        }
        // REPLACE BODY
        //replaceAllBodyElements(doc.getBodyElements(), placeHolder, replaceText);
        return doc;
    }

    private static void replaceAllBodyElements(List<IBodyElement> bodyElements, String placeHolder, String replaceText) {
        for (IBodyElement bodyElement : bodyElements) {
            if (bodyElement.getElementType().compareTo(BodyElementType.PARAGRAPH) == 0) {
                replaceParagraph((XWPFParagraph) bodyElement, placeHolder, replaceText);
            }
            if (bodyElement.getElementType().compareTo(BodyElementType.TABLE) == 0) {
                replaceTable((XWPFTable) bodyElement, placeHolder, replaceText);
            }
        }
    }

    private static void replaceTable(XWPFTable table, String placeHolder, String replaceText) {
        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                replaceAllBodyElements(cell.getBodyElements(), placeHolder, replaceText);
            }
        }
    }

    private static void replaceParagraph(XWPFParagraph paragraph, String placeHolder, String replaceText) {
        for (XWPFRun r : paragraph.getRuns()) {
            String text = r.getText(r.getTextPosition());
            if (text != null && text.contains(placeHolder)) {
                text = text.replace(placeHolder, replaceText);
                r.setText(text, 0);
            }
        }
    }
}
