package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.ArrayAccessCostModel;
import pl.aaugustyniak.algorithms_datastructs.unionfind.UnionFind;

/**
 *
 * @author artur
 */
@Ignore
public class SetBasedUnionFindTest {

    protected UnionFind<String> uf;
    private String[][] testConnections;

    @Before
    public void setUp() {
        this.setUnionFinder();
        testConnections = new String[11][2];
        testConnections[0] = new String[]{"4", "3"};
        testConnections[1] = new String[]{"3", "8"};
        testConnections[2] = new String[]{"6", "5"};
        testConnections[3] = new String[]{"9", "4"};
        testConnections[4] = new String[]{"2", "1"};
        testConnections[5] = new String[]{"8", "9"};
        testConnections[6] = new String[]{"5", "0"};
        testConnections[7] = new String[]{"7", "2"};
        testConnections[8] = new String[]{"6", "1"};
        testConnections[9] = new String[]{"1", "0"};
        testConnections[10] = new String[]{"6", "7"};
        for (int i = 0; i < 10; i++) {
            uf.addElement(Integer.toString(i));
        }
    }

    protected void setUnionFinder() {
        uf = new SetBasedUnionFind<>();
    }

    @Test
    public void testUfComponentCounter() {
        uf.union("3", "4");
        uf.union("3", "5");
        uf.union("4", "5");
        uf.union("5", "4");
        uf.union("5", "5");
        Integer exp = 8;
        Integer act = uf.componentsCount();
        assertEquals(exp, act);
    }

    @Test
    public void testSymmetricConnected() {
        uf.union("3", "4");
        uf.union("4", "4");
        boolean act = uf.areConnected("3", "4")
                && uf.areConnected("4", "3")
                && uf.areConnected("4", "4");
        assertTrue(act);
    }

    @Test
    public void testReflectiveConnected() {
        uf.union("3", "4");
        uf.union("4", "4");
        uf.union("4", "5");
        uf.union("8", "3");
        boolean act = uf.areConnected("3", "3") && uf.areConnected("4", "4");
        assertTrue(act);
    }

    @Test
    public void testTransitiveAndSymmetricConnected() {
        uf.union("1", "2");
        uf.union("2", "3");
        uf.union("3", "4");
        uf.union("4", "4");
        uf.union("4", "5");
        uf.union("8", "3");
        boolean act = uf.areConnected("1", "3") && uf.areConnected("3", "1");
        assertTrue(act);
    }

    /**
     * Desired Output: 4 - 3 3 - 8 6 - 5 9 - 4 2 - 1 5 - 0 7 - 2 6 - 1
     */
    @Test
    public void testCourseClient() {

        for (String[] strings : testConnections) {
            String p = strings[0];
            String q = strings[1];
            if (!uf.areConnected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " - " + q);
            }
        }
        
        System.out.println("End of " + this.uf.toString() + " run.");
        ArrayAccessCostModel costModel = (ArrayAccessCostModel) uf;
        int cost = costModel.getCost();
        System.out.println("For " + testConnections.length + " connections, array access model cost was: " + cost);
    }

}
