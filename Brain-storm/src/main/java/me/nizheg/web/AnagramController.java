package me.nizheg.web;

import java.util.List;

import me.nizheg.service.SimpleAnagramService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("anagram")
public class AnagramController {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SimpleAnagramService anagramService;

	@RequestMapping
	public String get() {
		return "anagram";
	}

	@RequestMapping("calc")
	public ModelAndView calculate(@RequestParam(value = "in", defaultValue = "") String in,
			@RequestParam(value = "isAccurate", defaultValue = "false") Boolean isAccurate,
			@RequestParam(value = "length", required = false) Integer length) {
		List<String> result;
		isAccurate &= length == null;
		if (isAccurate) {
			result = anagramService.getAccurateAnagrams(in);
		} else {
			result = anagramService.getAnagrams(in, length);
		}
		return new ModelAndView("anagram", "results", result);
	}

}
