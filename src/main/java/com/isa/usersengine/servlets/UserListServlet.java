package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.FreemarkerConf;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/users-list")
public class UserListServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(FindUserByIdServlet.class.getName());

    @Inject
    private FreemarkerConf templateProvider;

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<User> usersList = usersRepositoryDao.getUsersList();

        Template template = templateProvider.getTemplate(getServletContext(), "users-list.ftlh");
        Map<String, Object> model = new HashMap<>();
        model.put("users", usersList);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}