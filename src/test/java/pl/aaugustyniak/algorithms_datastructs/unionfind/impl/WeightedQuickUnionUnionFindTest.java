package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import org.junit.Ignore;

/**
 *
 * @author artur
 */
@Ignore
public class WeightedQuickUnionUnionFindTest extends SetBasedUnionFindTest {

    @Override
    protected void setUnionFinder() {
        uf = new WeightedQuickUnionUnionFind();
    }
}
