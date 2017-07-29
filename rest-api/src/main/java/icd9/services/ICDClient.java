package icd9.services;

import icd9.classification.Record;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.enterprise.inject.Default;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
@Default
public class ICDClient {

    private List<Record> records;

    private static final String REMOTE_ICD9_XLS
            = "https://danepubliczne.gov.pl/dataset"
            + "/a5f6b1ae-211a-4481-885e-ba9ff54c5ad4"
            + "/resource/a3647fe8-8e50-4836-b816-ec6861fac35f"
            + "/download/icd-9plw.5.33.xls";

    private static final String XLS_FILE_PATH = "icd-9plw.5.33.xls";
    private static final File XLS_FILE = new File(XLS_FILE_PATH);

    ICDClient() {
        records = new ArrayList<>();
        if (!XLS_FILE.exists()) {
            this.copyRemoteXlsFile();
        }
        this.readXlsFile();
    }

    List<Record> getICDRecords() {
        return records;
    }

    private void copyRemoteXlsFile() {
        try(InputStream inputStream = new URL(REMOTE_ICD9_XLS).openStream()) {
            Files.copy(inputStream, XLS_FILE.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readXlsFile() {
        try(InputStream inputStream = new FileInputStream(XLS_FILE)) {
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            int rows = sheet.getPhysicalNumberOfRows();

            for (int r = 2; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    Record record = new Record();
                    record.setSectionNumber(row.getCell(0).toString());
                    record.setSection(row.getCell(1).toString());
                    record.setSubsectionNumber(row.getCell(2).toString());
                    record.setSubsection(row.getCell(3).toString());
                    record.setMainCategoryNumber(row.getCell(4).toString());
                    record.setMainCategory(row.getCell(5).toString());
                    if (row.getCell(6) != null) {
                        record.setDetailedCategoryNumber(row.getCell(6).toString());
                    }
                    if (row.getCell(7) != null) {
                        record.setDetailedCategory(row.getCell(7).toString());
                    }
                    records.add(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
