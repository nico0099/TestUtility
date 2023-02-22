package org.example.reader;

import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.deloitte.excel.GenericReaderSheet;
import org.example.domain.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class PersonReaderSheet implements GenericReaderSheet<List<Person>> {

    @Override
    public int getSkipHeaderRows() {
        return 1;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public List<Person> readContent(Workbook workbook, Sheet sheet, Iterator<Row> iterator) {
        List<Person> list = new ArrayList<>();
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            if(currentRow.getRowNum() != 0) {
                Person person = new Person();
                person.setName(cellIterator.next().getStringCellValue());
                person.setSurname(cellIterator.next().getStringCellValue());
                person.setAge((int) cellIterator.next().getNumericCellValue());
                list.add(person);
            }

        }

        return list;
    }
}