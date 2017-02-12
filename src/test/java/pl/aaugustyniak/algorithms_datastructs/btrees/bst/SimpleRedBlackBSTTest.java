package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import org.junit.Before;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.SimpleRedBlackBST;

/**
 *
 * @author aaugustyniak
 */
public class SimpleRedBlackBSTTest extends RedBlackBSTTest {

    @Before
    @Override
    public void setUp() {
        tree = new SimpleRedBlackBST<>();
        initValues();
    }

}
