package ssau.lab1.mivs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LabController {

    private final LabService labService;

    public LabController(LabService labService) {
        this.labService = labService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Result result = labService.getResult();

        model.addAttribute("labels", result.getLabels());
        model.addAttribute("counts", result.getCounts());
        model.addAttribute("empiricalDensity", result.getEmpiricalDensity());
        model.addAttribute("theoreticalDensity", result.getTheoreticalDensity());
        model.addAttribute("chi2", String.format("%.4f", result.getChi2()));
        model.addAttribute("threshold", result.getThreshold());
        model.addAttribute("n", result.getN());
        model.addAttribute("nBins", result.getNBins());

        boolean adequate = result.getChi2() < result.getThreshold();
        model.addAttribute("adequate", adequate);

        return "index";
    }
}