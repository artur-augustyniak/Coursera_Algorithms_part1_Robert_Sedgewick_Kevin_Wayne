package pl.aaugustyniak.algorithms_datastructs.nsum.impl;

import java.util.Random;
import pl.aaugustyniak.algorithms_datastructs.ArrayAccessCostModel;
import pl.aaugustyniak.algorithms_datastructs.TimeCost;
import pl.aaugustyniak.algorithms_datastructs.nsum.ThreeSum;

/**
 *
 * @author artur
 */
public class BruteForceThreeSum implements ThreeSum, ArrayAccessCostModel, TimeCost {

    private final Integer intsNum;

    private Integer cost = 0;
    private Long microtime;
    private final Random r;
    

    public BruteForceThreeSum(Integer intsNum) {
        this.intsNum = intsNum;
        this.r = new Random(456);
        
    }

    @Override
    public void run() {
        int len = this.intsNum;
        int co = 0;
        int[] digits = new int[this.intsNum];
        //int[] digits = {30, -40, -20, -10, 40, 0, 10, 5};
        for (int i = 0; i < this.intsNum; i++) {
            digits[i] = this.r.nextInt(40) - this.r.nextInt(41);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (digits[i] + digits[j] + digits[k] == 0) {
                        co++;
                    }
                }
            }
        }
        this.cost = co;
        long stopTime = System.currentTimeMillis();
        this.microtime = stopTime - startTime;
    }

    public void setVerbose(boolean verbose) {
        
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    @Override
    public long getMicrotime() {
        return this.microtime;
    }

}
