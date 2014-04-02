package me.nizheg.web;

import java.util.List;

import me.nizheg.service.SimpleSearchService;
import me.nizheg.web.annotation.WordTypeFilterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("search")
@WordTypeFilterable
public class SearchController {

	@Autowired
	private SimpleSearchService searchService;

	@RequestMapping
	public String get(@RequestParam(value = "in", defaultValue = "") String in,
			@ModelAttribute(WordTypeFilterAdvice.WORD_TYPE_VALUES_ATTRIBUTE) List<String> values, Model model) {
		model.addAttribute("results", searchService.search(in, values));
		return "search";
	}

}
