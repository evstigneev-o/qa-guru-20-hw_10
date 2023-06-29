import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipArchiveTests {
    String csvFile = "myfile.csv";
    String pdfFile = "samplepdf.pdf";
    String xlsxFile = "file_example_XLSX_10.xlsx";

    @Test
    public void csvTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/archive.zip")) {
            ZipEntry csvEntry = zipFile.getEntry(csvFile);
            try (InputStream stream = zipFile.getInputStream(csvEntry)) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(stream));
                List<String[]> content = csvReader.readAll();
                assertEquals(content.size(), 2);
                assertArrayEquals(new String[]{"firstName", "Sergey"}, content.get(0));
                assertArrayEquals(new String[]{"secondName", "Ivanov"}, content.get(1));
            }
        }
    }

    @Test
    public void pdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/archive.zip")) {
            ZipEntry pdfEntry = zipFile.getEntry(pdfFile);
            try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
                PDF pdf = new PDF(stream);
                assertEquals("Nevrona Designs", pdf.producer);
            }
        }
    }

    @Test
    public void xlsxTest() throws Exception {
        try (ZipFile zipFile = new ZipFile("src/test/resources/archive.zip")) {
            ZipEntry xlsxEntry = zipFile.getEntry(xlsxFile);
            try (InputStream stream = zipFile.getInputStream(xlsxEntry)) {
                XLS xlsx = new XLS(stream);
                String actualName = xlsx.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                assertEquals("Dulce", actualName);
            }
        }
    }
}

