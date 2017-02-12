package pl.aaugustyniak.algorithms_datastructs.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author aaugustyniak
 */
public class Comparators {

    public static void main(String[] args) {

        List<Record> records = new ArrayList<>();
        records.add(new Record("Anna", "Tuś", 34));
        records.add(new Record("Hrywald", "Dupny", 13));
        records.add(new Record("Zdzisław", "Anioł", 53));
        records.add(new Record("Zdzisłow", "Aba", 2));

        Collections.sort(records, Record.BY_FNAME);
        System.out.println(records.toString());

        Collections.sort(records, Record.BY_LNAME);
        System.out.println(records.toString());

        Collections.sort(records, Record.BY_ID);
        System.out.println(records.toString());
    }
}
