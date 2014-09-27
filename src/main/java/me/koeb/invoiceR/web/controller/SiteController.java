package me.koeb.invoiceR.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteController {

    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    // this is parsed via template:
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        LOG.info("Index Page Loaded");
        return "index";
    }

}
