package me.nizheg.web;

import me.nizheg.service.SimpleGapoifikaService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gapoifika")
public class GapoifikaController {

	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private SimpleGapoifikaService gapoifikaService;

	@RequestMapping
	public String get() {
		return "gapoifika";
	}

	@RequestMapping("calc")
	public ModelAndView calculate(@RequestParam(value = "in", defaultValue = "") String in) {
		return new ModelAndView("gapoifika", "results", gapoifikaService.calculate(in));
	}

}
