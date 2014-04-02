package me.nizheg.web;

import java.util.List;

import me.nizheg.web.annotation.WordTypeFilterable;
import me.nizheg.web.backing.WordTypeFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(annotations = { WordTypeFilterable.class })
public class WordTypeFilterAdvice {

	public static final String WORD_TYPE_VALUES_ATTRIBUTE = "wordTypeValues";

	@Autowired
	private WordTypeFilter wordTypeFilter;

	@ModelAttribute
	public void populateModel(@RequestParam(value = "wordType", required = false) List<String> values, Model model) {
		model.addAttribute(WORD_TYPE_VALUES_ATTRIBUTE, values);
		model.addAllAttributes(wordTypeFilter.process(values));
	}

}
