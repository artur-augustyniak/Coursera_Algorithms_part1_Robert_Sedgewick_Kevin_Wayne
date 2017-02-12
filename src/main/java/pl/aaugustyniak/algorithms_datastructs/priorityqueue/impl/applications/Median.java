package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.applications;

import pl.aaugustyniak.algorithms_datastructs.selectionproblem.HoareQuickSelector;

/**
 *
 * @author aaugustyniak
 */
public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Integer[] nums = {4, 1, 3, 5};
        
        HoareQuickSelector<Integer> s = new HoareQuickSelector();
        
        System.out.println(s.median(nums));
        
    }
    

    
}
