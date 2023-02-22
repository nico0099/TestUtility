package org.example.writer;

import lombok.Data;
import org.deloitte.excel.GenericExcel;
import org.deloitte.excel.GenericWriterSheet;
import org.example.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonExcelWriterService extends GenericExcel<GenericWriterSheet> {

    private List<Person> personList = new ArrayList<>();

    @Override
    public void initializeSheet() {
        this.sheets.add(new PersonWriterSheet(personList, "Test person sheet"));
    }

}