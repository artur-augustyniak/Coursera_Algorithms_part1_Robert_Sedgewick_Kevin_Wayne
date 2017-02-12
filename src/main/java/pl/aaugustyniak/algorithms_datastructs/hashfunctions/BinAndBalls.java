package pl.aaugustyniak.algorithms_datastructs.hashfunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Separate chaining example
 * @author aaugustyniak
 */
public class BinAndBalls {

    private final int size;
    private final List<Object> container;

    public BinAndBalls(int size) {
        this.size = size;
        container = Arrays.asList(new Object[size]);

    }

    public void dumpHistogram() {
        Integer sum = 0;
        for (Object object : container) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer> bin = (ArrayList<Integer>) object;
            if (bin != null) {
                sum += bin.size();
                System.out.print(bin.size() + " ");
            }
        }

        System.out.println("Suma " + sum);
    }

    public int getSize() {
        return size;
    }

    public void intPosition(Integer i) {
        int hash = hash(i);
        @SuppressWarnings("unchecked")
        ArrayList<Integer> bin = (ArrayList<Integer>) container.get(hash);
        if (bin == null) {
            System.err.println("No such bin");
        } else {
            int binIdx = 0;
            Integer res = null;
            for (Integer integer : bin) {
                if (integer.equals(i)) {
                    res = integer;
                    break;
                }
                binIdx++;
            }
            if (res == null) {
                System.err.println("No such int in bin: " + hash);
            } else {
                System.out.println("int " + i + " found at bin: " + hash + " position: " + binIdx);
            }

        }

    }

    public void putInt(Integer i) {
        int hash = hash(i);
        @SuppressWarnings("unchecked")
        ArrayList<Integer> bin = (ArrayList<Integer>) container.get(hash);
        if (bin == null) {
            bin = new ArrayList<>();
            container.set(hash, bin);
        }
        bin.add(i);
    }

    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % size; //take 31 LSB bits
    }

    public static void main(String[] args) {
        BinAndBalls bnb = new BinAndBalls(100);
        Random r = new Random(42);
        int balls = 200;
        for (int i = 0; i < balls; i++) {
            Integer ri = r.nextInt(balls);
            bnb.putInt(ri);
        }
        bnb.dumpHistogram();

        for (int i = 0; i < balls; i++) {
            Integer ri = r.nextInt(balls);
            bnb.intPosition(i);
        }

    }

}
