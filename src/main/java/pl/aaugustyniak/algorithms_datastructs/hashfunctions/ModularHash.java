package pl.aaugustyniak.algorithms_datastructs.hashfunctions;

import java.util.Random;

/**
 * Modulo wrap of hash to M array number beetween 0 and M-1
 *
 * @author aaugustyniak
 */
public class ModularHash {

    private static int M = 1000;
    public static Random r = new Random(42);
    public static int n = 20;

    public static void main(String[] args) {

        System.err.println(heisenBadHash("polygenelubricants"));
        
//        for (int i = n * -1; i < n; i++) {
//            if (badHash(i) > M - 1 || badHash(i) < 0) {
//                System.err.println(badHash(i));
//                break;
//            }
//        }
//        for (int i = n * -1; i < n; i++) {
//            if (properHash(i) > M - 1 || properHash(i) < 0) {
//                System.err.println(properHash(i));
//                break;
//            }
//            
//            System.out.println(properHash(i));
//        }
    }

    public static int badHash(Object key) {
        return key.hashCode() % M;
    }

    /**
     * Hash string polygenelubricants
     *
     * @param key
     * @return
     */
    public static int heisenBadHash(Object key) {
        int hk = key.hashCode();
        int m = Integer.MAX_VALUE+ 1;
        int abs = Math.abs(hk);
        int ret = abs % M;
        return ret;
    }

    public static int properHash(Object key) {
        return (key.hashCode() & 0x7fffffff) % M; //LSB
    }

}
