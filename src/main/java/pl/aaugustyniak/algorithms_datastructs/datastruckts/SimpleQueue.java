package pl.aaugustyniak.algorithms_datastructs.datastruckts;

/**
 *
 * @author artur
 */
public interface SimpleQueue<T> extends Iterable<T> {

    public void enqueue(T item);

    public T dequeue();

    public boolean isEmpty();

    public int size();

}
