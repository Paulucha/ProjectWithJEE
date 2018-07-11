package com.isa.usersengine.com.isa.usersengine.servlets;

import com.isa.usersengine.freemarker.FreemarkerConf;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("welcomeUser")
public class WelcomeUserServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeUserServlet.class.getName());

    @Inject
    private FreemarkerConf freemarkerConf;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if (name == null || name.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Template template = freemarkerConf.getTemplate(getServletContext(), "welcome-user.ftlh");
        Map<String, String> nowaMapa = new HashMap<>();
        nowaMapa.put("name", name);

        PrintWriter printWriter = resp.getWriter();
        try {
            template.process(nowaMapa, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage());

        }


    }
}
