package pl.aaugustyniak.algorithms_datastructs.hashfunctions;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author aaugustyniak
 */
public class TestComplexObject {
    private final String name;
    private final Date access;
    private final double ammount;
    private final String[] smth = {"sdf", "sdfsdf"};

    public TestComplexObject(String name, Date access, double ammount) {
        this.name = name;
        this.access = access;
        this.ammount = ammount;
    }

    @Override
    public int hashCode() {
        int hash = 7; // nonzero constant
        // 59 small prime
        hash = 59 * hash + this.name.hashCode();
        hash = 59 * hash + Objects.hashCode(this.access);
        hash = 59 * hash + ((Double) ammount).hashCode();
        hash = 59 * hash + Arrays.deepHashCode(smth);
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
        final TestComplexObject other = (TestComplexObject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.access, other.access)) {
            return false;
        }
        if (Double.doubleToLongBits(this.ammount) != Double.doubleToLongBits(other.ammount)) {
            return false;
        }
        return true;
    }
    
}
