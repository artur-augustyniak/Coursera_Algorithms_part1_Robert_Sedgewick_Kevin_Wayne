package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import pl.aaugustyniak.algorithms_datastructs.btrees.impl.IterativeBST;
import org.junit.Before;

/**
 *
 * @author aaugustyniak
 */
public class IterativeBSTTest extends BSTTest {

    @Before
    @Override
    public void setUp() {
        tree = new IterativeBST<>();
        initValues();

    }
}
