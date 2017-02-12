package pl.aaugustyniak.algorithms_datastructs.hashfunctions;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author aaugustyniak
 */
public class HashProber {

    public static class TestObject {

        public int x;

        public TestObject(int x) {
            this.x = x;
        }

        @Override
        public int hashCode() {
            //return super.hashCode();// wrong if objects equal hash code will be not
            int hash = 7;
            hash = 79 * hash + this.x;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TestObject other = (TestObject) obj;
            if (this.x != other.x) {
                return false;
            }
            return true;
        }

    };

    public static Random r = new Random(42);
    public static int n = 10;

    public static void main(String[] args) {
        //integerProber();
        //customObjectProber();
        customComplexObjectProber();
    }

    public static void customComplexObjectProber() {
        for (int i = 0; i < n; i++) {
            double fill = 12.0;
            String name = "Anna";
            Date time = new Date();

            double fill2 = 12.0;
            String name2 = "Anna";
            Date time2 = new Date();

            if (r.nextBoolean()) {
                fill2 = r.nextDouble();
                name2 = "sdf";
                time2 = new Date(2010, 1, 2);
            }

            TestComplexObject o = new TestComplexObject(name, time, fill);
            TestComplexObject o2 = new TestComplexObject(name2, time2, fill2);

            System.out.println("Equality " + o.equals(o2));
            System.out.println("Equality of hash code " + (o.hashCode() == o2.hashCode()));

            System.out.println("Object: " + o + " - hashCode: " + o.hashCode());
            System.out.println();
        }
    }

    public static void customObjectProber() {
        for (int i = 0; i < n; i++) {
            int fill = 12;

            if (r.nextBoolean()) {
                fill = r.nextInt(n);
            }

            TestObject o = new TestObject(fill);
            TestObject o2 = new TestObject(12);

            System.out.println("Equality " + o.equals(o2));
            System.out.println("Equality of hash code " + (o.hashCode() == o2.hashCode()));

            System.out.println("Object: " + o + " - hashCode: " + o.hashCode());
            System.out.println();
        }
    }

    public static void integerProber() {
        for (int i = 0; i < n; i++) {
            Integer ri = r.nextInt(n);
            Object ori = (Object) ri;
            System.out.println("Object: " + ri + " - hashCode: " + ri.hashCode());
            System.out.println("Object casted hashCode: " + ori);
        }
    }

}
