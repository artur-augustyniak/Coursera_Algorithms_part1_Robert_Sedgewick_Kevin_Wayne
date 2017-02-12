package pl.aaugustyniak.algorithms_datastructs.comparators;

import java.util.Comparator;

/**
 *
 * @author aaugustyniak
 */
public class Record {

    public String firstName;
    public String lastName;
    public Integer id;
    public static final Comparator<Record> BY_FNAME = new fNameComparator();
    public static final Comparator<Record> BY_LNAME = new lNameComparator();
    public static final Comparator<Record> BY_ID = new idComparator();

    public Record(String firstName, String lastName, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public static class fNameComparator implements Comparator<Record> {

        @Override
        public int compare(Record t, Record t1) {
            return t.firstName.compareToIgnoreCase(t1.firstName);
        }
    }

    public static class lNameComparator implements Comparator<Record> {

        @Override
        public int compare(Record t, Record t1) {
            return t.lastName.compareToIgnoreCase(t1.lastName);
        }
    }

    public static class idComparator implements Comparator<Record> {

        @Override
        public int compare(Record t, Record t1) {
            return t.id.compareTo(t1.id);
        }
    }

    public String toString() {
        return "Record{" + "firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + '}';
    }
}
