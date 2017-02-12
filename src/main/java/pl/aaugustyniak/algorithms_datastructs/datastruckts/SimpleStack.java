package pl.aaugustyniak.algorithms_datastructs.datastruckts;

/**
 *
 * @author artur
 * @param <T>
 */
public interface SimpleStack<T> extends Iterable<T> {

    public void push(T item);

    public T pop();

    public boolean isEmpty();

    public T peek();

}
