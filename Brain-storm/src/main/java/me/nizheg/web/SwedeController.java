package me.nizheg.web;

import me.nizheg.service.SimpleSwedeService;
import me.nizheg.web.annotation.WordTypeFilterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("swedes")
@WordTypeFilterable
public class SwedeController {

    @Autowired
    private SimpleSwedeService swedeService;

    @RequestMapping
    public String get(@RequestParam(value = "in", defaultValue = "") String in,
        @RequestParam(value = "from", defaultValue = "2") int from,
        @RequestParam(value = "to", defaultValue = "1") int to,
        @ModelAttribute(WordTypeFilterAdvice.WORD_TYPE_VALUES_ATTRIBUTE) List<String> values, Model model) {
        model.addAttribute("results", swedeService.search(in, from, to, values));
        return "swede";
    }

}
