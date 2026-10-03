package ssau.lab1.mivs;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class LabService {

    private final int n = 1000;
    private final double a = 67.0;
    private final int nBins = 15;
    private final double threshold = 23.7;
    private final Random random = new Random();

    private double[] getX() {
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            double r = random.nextDouble();
            x[i] = a * Math.sqrt(r * (2 - r));
        }
        return x;
    }

    private double[] getBounds() {
        double[] bounds = new double[nBins + 1];
        bounds[0] = 0.0;
        bounds[nBins] = a;
        for (int k = 1; k < nBins; k++) {
            double p = (double) k / nBins;
            bounds[k] = a * Math.sqrt(p * (2 - p));
        }
        return bounds;
    }

    private int[] getCounts(double[] x, double[] bounds) {
        int[] counts = new int[nBins];
        for (double value : x) {
            boolean found = false;
            for (int k = 0; k < nBins && !found; k++) {
                if (value >= bounds[k] && value < bounds[k + 1]) {
                    counts[k]++;
                    found = true;
                }
            }
        }
        return counts;
    }

    private double chi2(int[] counts) {
        double expected = (double) n / nBins;
        double chi2 = 0.0;
        for (int k = 0; k < nBins; k++) {
            double diff = counts[k] - expected;
            chi2 += diff * diff / expected;
        }
        return chi2;
    }

    private double[] getEmpiricalDensity(int[] counts, double[] bounds) {
        double[] density = new double[nBins];
        for (int k = 0; k < nBins; k++) {
            double width = bounds[k + 1] - bounds[k];
            density[k] = counts[k] / ((double) n * width);
        }
        return density;
    }

    private double[] getTheoreticalDensity(double[] bounds) {
        double[] theo = new double[nBins];
        for (int k = 0; k < nBins; k++) {
            double mid = (bounds[k] + bounds[k + 1]) / 2.0;
            theo[k] = mid / (a * Math.sqrt(a * a - mid * mid));
        }
        return theo;
    }

    public Result getResult() {
        double[] x = getX();
        double[] bounds = getBounds();
        int[] counts = getCounts(x, bounds);
        double chi2 = chi2(counts);
        double[] empirical = getEmpiricalDensity(counts, bounds);
        double[] theoretical = getTheoreticalDensity(bounds);

        String[] labels = new String[nBins];
        for (int k = 0; k < nBins; k++) {
            labels[k] = String.format("%.2f–%.2f", bounds[k], bounds[k + 1]);
        }

        return Result.builder()
                .x(x)
                .counts(counts)
                .labels(labels)
                .empiricalDensity(empirical)
                .theoreticalDensity(theoretical)
                .chi2(chi2)
                .threshold(threshold)
                .nBins(nBins)
                .n(n)
                .build();
    }
}