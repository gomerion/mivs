package ssau.lab2.mivs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor 
public class LabController {
    
    private final LabService labService;

    @GetMapping()
    public String getMethodName(Model model) {
        return "index";
    }
    
    @PostMapping("/result")
    public String getResult(@RequestParam double lamda1, 
                           @RequestParam double lamda2, 
                           Model model) {
        Result result = labService.getResult(lamda1, lamda2);
        model.addAttribute("result", result);
        return "index";
    }
}