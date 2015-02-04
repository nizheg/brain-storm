package me.nizheg.web;

import me.nizheg.service.SimpleDismemberService;
import me.nizheg.web.annotation.WordTypeFilterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("dismember")
@WordTypeFilterable
public class DismemberController {

	@Autowired
	private SimpleDismemberService dismemberService;

    @RequestMapping
   	public String get(@RequestParam(value = "val", defaultValue = "") String val,
   			@ModelAttribute(WordTypeFilterAdvice.WORD_TYPE_VALUES_ATTRIBUTE) List<String> values, Model model) {
   		model.addAttribute("results", dismemberService.calculate(val, values));
   		return "dismember";
   	}
}
