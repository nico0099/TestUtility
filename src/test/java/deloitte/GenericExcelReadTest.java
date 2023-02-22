package deloitte;

import junit.framework.TestSuite;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.domain.Person;
import org.example.reader.PersonExcelReaderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenericExcelReadTest extends TestSuite {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String PATH_NAME = "src/person-list.xlsx";
    private List<Person> listExpected;
    private PersonExcelReaderService personExcelReaderService;

    @BeforeAll
    public void before() {
        listExpected = new ArrayList<>();
        listExpected.add(new Person("Mario", "Rossi", 56));
        listExpected.add(new Person("Luca", "Rossi", 35));
        listExpected.add(new Person("Sofia", "Rossi", 17));
        personExcelReaderService = new PersonExcelReaderService();
        personExcelReaderService.initializeSheet();
    }

    @Test
    public void testReadInputStream() {
        try (FileInputStream inputStream = new FileInputStream(PATH_NAME)) {
            List<Object> sheets = personExcelReaderService.readInputStream(inputStream);
            for (Object sheet : sheets) {
                List<Person> listActual = (List<Person>) sheet;
                assertEquals(listExpected, listActual);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "test read input stream passed");
    }

    @Test
    public void testReadFile() {
        try (FileInputStream inputStream = new FileInputStream(PATH_NAME)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            List<Object> sheets1 = personExcelReaderService.readFile((XSSFWorkbook) workbook);
            for (Object sheet: sheets1) {
                List<Person> listActual = (List<Person>) sheet;
                assertEquals(listExpected, listActual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "test read file passed");
    }
}
