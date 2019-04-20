import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DocumentApplication {
    public static void main(String[] args) {
        String dirPath = "D:\\TESTDOC\\org";
        String resultPath = "D:\\TESTDOC\\resultDir\\";
        List<File> files = FileManagement.getFilesFromDir(dirPath);
        files.forEach(x -> {
                    System.out.println(x.getName());
                    XWPFDocument doc = null;
                    try {
                        doc = new XWPFDocument(OPCPackage.open(x));
                        DocumentUtil.replacePOI(doc, "3", "4");
                        DocumentUtil.replacePOI(doc, "106", "108");
                        DocumentUtil.replacePOI(doc, "18", "05");
                    } catch (IOException | InvalidFormatException e) {
                        e.printStackTrace();
                    }

                    try (OutputStream os = new FileOutputStream(new File(resultPath + x.getName()))) {
                        assert doc != null;
                        doc.write(os);
                        doc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
