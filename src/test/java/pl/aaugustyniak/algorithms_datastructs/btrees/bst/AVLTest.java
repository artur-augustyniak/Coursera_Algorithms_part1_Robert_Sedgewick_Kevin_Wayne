package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import pl.aaugustyniak.algorithms_datastructs.btrees.impl.AVL;
import org.junit.Before;

/**
 *
 * @author artur
 */
public class AVLTest extends BSTTest {

    @Before
    @Override
    public void setUp() {
        tree = new AVL<>();
        initValues();
    }
}
