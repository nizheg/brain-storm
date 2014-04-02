package me.nizheg.web;

import java.util.List;

import me.nizheg.service.SimpleGapoifikaService;
import me.nizheg.web.annotation.WordTypeFilterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("gapoifika")
@WordTypeFilterable
public class GapoifikaController {

	@Autowired
	private SimpleGapoifikaService gapoifikaService;

	@RequestMapping
	public String get() {
		return "gapoifika";
	}

	@RequestMapping("calc")
	public String calculate(@RequestParam(value = "in", defaultValue = "") String in,
			@RequestParam(value = "isAccurate", defaultValue = "false") Boolean isAccurate,
			@ModelAttribute(WordTypeFilterAdvice.WORD_TYPE_VALUES_ATTRIBUTE) List<String> values, Model model) {
		model.addAttribute("results", gapoifikaService.calculate(in, values, isAccurate));
		return "gapoifika";
	}

}
