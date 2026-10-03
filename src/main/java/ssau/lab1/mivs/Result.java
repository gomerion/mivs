package ssau.lab1.mivs;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result {

    private double[] x;
    private int[] counts;
    private String[] labels;
    private double[] empiricalDensity;
    private double[] theoreticalDensity;
    private double chi2;
    private double threshold;
    private int nBins;
    private int n;
}