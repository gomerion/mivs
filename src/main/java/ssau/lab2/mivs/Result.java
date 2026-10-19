package ssau.lab2.mivs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor 
public class Result {
    
    private double[] tau;
    private double[] t;

}
