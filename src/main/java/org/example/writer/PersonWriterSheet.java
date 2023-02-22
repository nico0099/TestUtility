package org.example.writer;

import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.deloitte.excel.GenericExcel;
import org.deloitte.excel.GenericWriterSheet;
import org.example.domain.Person;

import java.util.Arrays;
import java.util.List;

@Data
public class PersonWriterSheet implements GenericWriterSheet {
    private List<Person> listPerson;
    private String sheetName;

    public PersonWriterSheet(List<Person> listPerson, String sheetName) {
        this.listPerson = listPerson;
        this.sheetName = sheetName;
    }

    @Override
    public List<String> getHeader() {
        return Arrays.asList(
                "Name",
                "Surname",
                "Age"
        );
    }

    @Override
    public void generateContent(Sheet sheet) {
        int rowIndex = 1;
        Row row;

        for(Person person: listPerson) {
            row = sheet.createRow(rowIndex++);
            GenericExcel.setCellValue(row, 0, person.getName());
            GenericExcel.setCellValue(row, 1, person.getSurname());
            GenericExcel.setCellValue(row, 2, person.getAge());
        }
    }

}