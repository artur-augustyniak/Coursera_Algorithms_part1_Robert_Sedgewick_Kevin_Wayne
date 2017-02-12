package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

/**
 *
 * @author artur
 */
public class QuickUnionFindPathCompressionTest extends SetBasedUnionFindTest {
    
    @Override
    protected void setUnionFinder() {
        uf = new QuickUnionFindPathCompression<>();
    }
    
}
