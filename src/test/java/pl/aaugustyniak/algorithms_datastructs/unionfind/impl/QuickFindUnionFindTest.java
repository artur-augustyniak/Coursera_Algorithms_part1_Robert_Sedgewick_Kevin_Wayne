package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import org.junit.Ignore;

/**
 *
 * @author artur
 */
@Ignore
public class QuickFindUnionFindTest extends SetBasedUnionFindTest {

    @Override
    protected void setUnionFinder() {
        uf = new QuickFindUnionFind();
    }
}
