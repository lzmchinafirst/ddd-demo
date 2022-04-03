package com.purang.manifest.adapter.web;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/index.htm")
    public ModelAndView index(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        Map<String, String> session = (Map<String, String>) req.getSession().getAttribute("session");
        JSONObject jsonObject = JSONObject.fromObject(session);
        mv.addObject("loginsession", jsonObject);
        return mv;
    }
}