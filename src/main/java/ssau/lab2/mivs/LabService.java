package ssau.lab2.mivs;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service 
public class LabService {

    private final int N = 1000;

    private final Random random = new Random();
    
    private double[] generateGeneralizedErlangFlow(double lamda1, double lamda2) {
 
        double[] tau = new double[N];

        for (int i = 0; i < N; i++) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();

            double x = -Math.log(r1) / lamda1;
            double y = -Math.log(r2) / lamda2;

            tau[i] = x + y;
        }

        return tau;
    }

    public double[] timeMoment(double lamda1, double lamda2, double[] tau) {

        double[] t = new double[N];
        t[0] = 0;

        for (int i = 1; i < N; i++) {
            t[i] = t[i - 1] + tau[i];
        }

        return t;
    }

    public Result getResult(double lamda1, double lamda2) {
        double[] tau = generateGeneralizedErlangFlow(lamda1, lamda2);
        double[] t = timeMoment(lamda1, lamda2, tau);
        return Result.builder()
                    .tau(tau)
                    .t(t)
                    .build();
    }

}