package com.isa.usersengine.com.isa.usersengine.servlets;

import com.isa.usersengine.com.isa.userengine.cdi.FileUploadProcessorBean;
import com.isa.usersengine.com.isa.userengine.cdi.com.isa.usersengine.exceptions.UserImageNotFound;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
    @Inject
    private FreemarkerConf templateProvider;
    @Inject
    private FileUploadProcessorBean fileUploadProcessorBean;

    private UsersRepositoryDao usersRepositoryDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usersRepositoryDao = new UsersRepositoryDaoBean();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String nameParam = req.getParameter("name");
        String loginParam = req.getParameter("login");
        String passwordParam = req.getParameter("password");
        String ageParam = req.getParameter("age");

        if (!isParamsValid(idParam, nameParam, loginParam, passwordParam, ageParam)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = new User();
        user.setId(Integer.parseInt(idParam));
        user.setName(nameParam);
        user.setLogin(loginParam);
        user.setPassword(passwordParam);
        user.setAge(Integer.parseInt(ageParam));

        Part filePart = req.getPart("image");
        File file = null;
        try {
            file = fileUploadProcessorBean.uploadImageFile(filePart);
            user.setImage("/images" + file.getName());
        } catch (UserImageNotFound userImageNotFound) {
            userImageNotFound.printStackTrace();
        }


        usersRepositoryDao.addUser(user);
    }

    private boolean isParamsValid(String... params) {

        for (String param : params) {
            if (param == null || param.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}