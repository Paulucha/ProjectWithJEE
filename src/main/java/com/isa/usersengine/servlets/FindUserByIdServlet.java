package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.MaxPulseBean;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.TemplateProvider;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(FindUserByIdServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Inject
    private MaxPulseBean maxPulseBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Integer id = Integer.parseInt(idParam);

        User userById = usersRepositoryDao.getUserById(id);

        Template template = templateProvider.getTemplate(getServletContext(), "find-user-by-id.ftlh");
        Map<String, Object> model = new HashMap<>();

        if (userById != null) {
            model.put("user", userById);

            Double pulse = 0D;

            if (userById.getGender().equals(Gender.MAN)) {
                pulse = maxPulseBean.getManMaxPulse(userById.getAge());
            } else if (userById.getGender().equals(Gender.WOMAN)) {
                pulse = maxPulseBean.getWomanPulse(userById.getAge());
            }

            if (pulse != 0D) {
                model.put("pulse", pulse);
            }

        } else {
            model.put("errorMessage", "User not found.");
        }

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}