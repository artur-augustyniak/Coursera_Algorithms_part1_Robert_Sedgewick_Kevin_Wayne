package pl.aaugustyniak.algorithms_datastructs.sorting.networks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author artur
 * @param <T>
 */
public class SortingNetwork<T extends Comparable<T>> {

    private final int elemCount;
    private List<T> networkPins;
    private final List<List<NetworkComparator>> comparators;

    private class NetworkComparator<T extends Comparable<T>> {

        private final int stPin;
        private final int ndPin;

        public NetworkComparator(int stPin, int ndPin) {
            this.stPin = stPin;
            this.ndPin = ndPin;
        }

        public void proceed() {
            if (networkPins.get(stPin).compareTo(networkPins.get(ndPin)) > 0) {
                swap(stPin, ndPin);
            }
        }
    }

    private void swap(int aId, int bId) {
        T tmp = networkPins.get(bId);
        networkPins.set(bId, networkPins.get(aId));
        networkPins.set(aId, tmp);

    }

    public SortingNetwork(int elemCount) {
        this.elemCount = elemCount;
        this.networkPins = new ArrayList<>(elemCount);
        comparators = new ArrayList<>(elemCount - 1);
        attachComparators();
    }

    private void attachComparators() {
        for (int i = 0; i < elemCount - 1; i++) {
            ArrayList<NetworkComparator> step = new ArrayList<>();
            for (int j = 0; j < elemCount - i -1 ; j++) {
                step.add(new NetworkComparator(j, j + 1));
            }
            comparators.add(step);
        }

    }

    public T[] sort(T[] input) {
        this.networkPins = Arrays.asList(input);
        for (List<NetworkComparator> step : comparators) {
            for (NetworkComparator c : step) {
                c.proceed();
            }
        }
        return (T[]) networkPins.toArray();
    }

}
