package com.isa.usersengine.com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.FreemarkerConf;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
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

@WebServlet("find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeUserServlet.class.getName());

    @Inject
    private FreemarkerConf freemarkerConf;

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idParam = req.getParameter("id");
        Integer id = Integer.parseInt(idParam);

        User userById = usersRepositoryDao.getUserById(id);

        Template template = freemarkerConf.getTemplate(getServletContext(), "find-user-by-id.ftlh");
        Map<String, Object> nowaMapa = new HashMap<>();
        nowaMapa.put("user", userById);
        Double pulse = 0D;


        PrintWriter printWriter = resp.getWriter();
        try {
            template.process(nowaMapa, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage());

        }


//        PrintWriter writer = resp.getWriter();
//
//
//        if (req.getParameter("id") == null) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        } else {
//
//            writer.println("<!DOCTYPE html>");
//            writer.println("<html>");
//            writer.println("<body>");
//            writer.println("<h1>");
//            writer.println(usersRepositoryDao.getUserById(Integer.valueOf(req.getParameter("id"))).toString());
//            writer.println("</h1>");
//            writer.println("</body>");
//            writer.println("</html>");
//        }
    }
}