package me.nizheg.web;

import me.nizheg.service.SimpleBrailleService;
import me.nizheg.web.annotation.WordTypeFilterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("braille")
@WordTypeFilterable
public class BrailleController {

    @Autowired
    private SimpleBrailleService simpleBrailleService;

    @RequestMapping
    public String get(@RequestParam(defaultValue = "false") boolean in1,
        @RequestParam(defaultValue = "false") boolean in2,
        @RequestParam(defaultValue = "false") boolean in3,
        @RequestParam(defaultValue = "false") boolean in4,
        @RequestParam(defaultValue = "false") boolean in5,
        @RequestParam(defaultValue = "false") boolean in6,
        Model model) {
        boolean[] in = {in1, in2, in3, in4, in5, in6};
        model.addAttribute("results", simpleBrailleService.getAllPossibleVariants(in));
        model.addAttribute("invertedResults", simpleBrailleService.getAllPossibleVariants(simpleBrailleService.invert(in)));
        return "braille";
    }
}
