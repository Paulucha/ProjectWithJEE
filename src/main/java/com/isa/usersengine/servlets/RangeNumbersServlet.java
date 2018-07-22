package com.isa.usersengine.servlets;


import com.isa.usersengine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/range-numbers")
public class RangeNumbersServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(RangeNumbersServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String numberParam = req.getParameter("number");
        Template template = templateProvider.getTemplate(getServletContext(), "range-numbers.ftlh");
        Map<String, Object> model = new HashMap<>();

        if (numberParam == null || numberParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            model.put("errorMessage", "Number should not be empty.");
        } else {
            Integer number = Integer.parseInt(numberParam);
            model.put("maxNumber", number);
        }

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}