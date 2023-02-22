package org.example.reader;

import lombok.Data;
import org.deloitte.excel.GenericExcel;
import org.example.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonExcelReaderService extends GenericExcel<PersonReaderSheet> {

    private List<Person> personList = new ArrayList<>();

    @Override
    public void initializeSheet() {
        this.sheets.add(new PersonReaderSheet());
    }
}