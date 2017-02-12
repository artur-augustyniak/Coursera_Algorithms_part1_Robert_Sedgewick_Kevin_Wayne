package pl.aaugustyniak.algorithms_datastructs.sorting;

/**
 * @see http://en.wikipedia.org/wiki/Kendall_tau_distance
 * @author
 */
public class KendallTau {

    private static long merge(Comparable[] a, Comparable[] aux, int left, int mid, int right) {
        long inversions = 0;
        int i, j;
        for (i = mid + 1; i > left; i--) {
            aux[i - 1] = a[i - 1];
        }
        for (j = mid; j < right; j++) {
            aux[right + mid - j] = a[j + 1];
        }
        for (int k = left; k <= right; k++) {
            if (less(aux, j, i)) {
                a[k] = aux[j--];
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
        return inversions;
    }

    private static long sort(Comparable[] a, Comparable[] aux, int left, int right) {
        long inversions = 0;
        if (right <= left) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        inversions += sort(a, aux, left, mid);
        inversions += sort(a, aux, mid + 1, right);
        inversions += merge(a, aux, left, mid, right);
        return inversions;
    }

    // count number of inversions - do not overwrite original input array
    public static long count(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return sort(b, aux, 0, a.length - 1);
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable[] a, int i, int j) {
        return (a[i].compareTo(a[j]) < 0);
    }

    // count number of inversions by brute force (for debugging only)
    private static long brute(Comparable[] a) {
        int inversions = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (less(a, j, i)) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    // return Kendall tau distance between two permutations
    public static long distance(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array dimensions disagree");
        }
        int N = a.length;

        int[] ainv = new int[N];
        for (int i = 0; i < N; i++) {
            ainv[a[i]] = i;
        }

        Integer[] bnew = new Integer[N];
        for (int i = 0; i < N; i++) {
            bnew[i] = ainv[b[i]];
        }

        return count(bnew);
    }

    // return a random permutation of size N
    public static int[] permutation(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * (i + 1));
            a[i] = a[r];
            a[r] = i;
        }
        return a;
    }

    public static void main(String[] args) {

        // two random permutation of size N
        int N = 20;
        int[] a = KendallTau.permutation(N);
        int[] b = KendallTau.permutation(N);

        // print initial permutation
        for (int i = 0; i < N; i++) {
            System.out.println(a[i] + " " + b[i]);
        }
        System.out.println();

        System.out.println("inversions = " + KendallTau.distance(a, b));
    }
}
