package me.nizheg.web;

import me.nizheg.service.SimpleDismemberService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dismember")
public class DismemberController {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SimpleDismemberService wordDismemberService;

	@Autowired
	private SimpleDismemberService movieDismemberService;

	@RequestMapping("movie")
	public ModelAndView getMovie() {
		return new ModelAndView("dismember", "path", "movie");
	}

	@RequestMapping("word")
	public ModelAndView getWord() {
		return new ModelAndView("dismember", "path", "word");
	}

	@RequestMapping("movie/calc")
	public ModelAndView calculateMovie(@RequestParam(value = "val", defaultValue = "") String val) {
		logger.debug("Get value [" + val + "]");
		ModelAndView model = new ModelAndView("dismember", "results", movieDismemberService.calculateMovie(val));
		model.addObject("path", "movie");
		return model;
	}

	@RequestMapping("word/calc")
	public ModelAndView calculateWord(@RequestParam(value = "val", defaultValue = "") String val) {
		logger.debug("Get value [" + val + "]");
		ModelAndView model = new ModelAndView("dismember", "results", wordDismemberService.calculateMovie(val));
		model.addObject("path", "word");
		return model;
	}

}
