package me.nizheg.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import me.nizheg.service.SimpleAnagramService;
import me.nizheg.service.SimpleTarkService;
import me.nizheg.service.SimpleTarkService.MultisetDiff;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("tark")
public class TarkController {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SimpleTarkService tarkService;
	@Autowired
	private SimpleAnagramService anagramService;

	@RequestMapping(value = "/diff", method = RequestMethod.GET)
	public ModelAndView getDifference(@RequestParam(value = "firstSeq", required = false) String firstSeq,
			@RequestParam(value = "secondSeq", required = false) String secondSeq, HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get difference is started: [" + this.toString() + "]. Parameters: " + "[" + firstSeq + "],["
					+ secondSeq + "]");
		}

		if (firstSeq == null) {
			firstSeq = "";
		}
		if (secondSeq == null) {
			secondSeq = "";
		}
		session.setAttribute("firstSeq", firstSeq);
		session.setAttribute("secondSeq", secondSeq);
		MultisetDiff diff = tarkService.calculateMultisetDiff(firstSeq, secondSeq);

		if (logger.isDebugEnabled()) {
			logger.debug("Difference is calculated: " + diff);
		}

		ModelAndView model = new ModelAndView("tark");
		model.addObject("firstElemComplement", diff.getFirstElemComplement());
		model.addObject("secondElemComplement", diff.getSecondElemComplement());
		return model;
	}

	@RequestMapping(value = "/anagram/{seq}", method = RequestMethod.GET)
	public ModelAndView getAnagramm(@PathVariable("seq") String seq,
			@RequestParam(value = "for", defaultValue = "1") Byte forWord) {
		List<String> anagrams = anagramService.getAnagrams(seq);
		ModelAndView model = new ModelAndView("tark", "anagrams", anagrams);
		model.addObject("forWord", forWord);
		return model;
	}

	@RequestMapping
	public String get() {
		return "tark";
	}

}
