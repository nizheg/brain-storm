package me.nizheg.web;

import java.util.List;

import me.nizheg.service.SimpleSearchService;
import me.nizheg.web.backing.WordTypeFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("search")
public class SearchController {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SimpleSearchService searchService;
	@Autowired
	private WordTypeFilter wordTypeFilter;

	@RequestMapping
	public ModelAndView get(@RequestParam(value = "in", defaultValue = "") String in,
			@RequestParam(value = "wordType", required = false) List<String> values) {

		ModelAndView model = new ModelAndView("search");
		model.addAllObjects(wordTypeFilter.process(values));
		model.addObject("results", searchService.search(in, values));
		return model;
	}

}
