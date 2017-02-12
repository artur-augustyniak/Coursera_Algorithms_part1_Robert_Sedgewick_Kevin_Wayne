package pl.aaugustyniak.algorithms_datastructs.unionfind;

/**
 * Dynamic connectivity.
 * Union-Find algorithm interface 
 * @author artur
 * @param <T>
 */
public interface UnionFind<T> {

    /**
     * Set union between elements
     *
     * @param p
     * @param q
     */
    public void union(T p, T q);

    /**
     * Equivalence relation.
     * symmetric: If p is connected to q, then q is connected to p.
     * transitive: If p is connected to q and q is connected to r, then p is connected to r.
     * reflexive: p is connected to p.
     * @param p
     * @param q
     * @return
     */
    public boolean areConnected(T p, T q);
    
    /**
     * Get nuber of current connected components.
     * @return 
     */
    public Integer componentsCount();
    /**
     * Remove distinct element from any connected component
     * @param p 
     */
    public void addElement(T p);
    
    /**
     * Add element as self contained ceonnected component
     * @param p 
     */
    public void removeElement(T p);

}
