package com.isa.usersengine.servlets;
import com.isa.usersengine.cdi.FileUploadProcessorBean;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.exceptions.UserImageNotFound;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/add-user")
@MultipartConfig
public class AddUserServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Inject
    FileUploadProcessorBean fileUploadProcessor;

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
            file = fileUploadProcessor.uploadImageFile(filePart);
            user.setImageURL("/images/" + file.getName());
        } catch (UserImageNotFound userImageNotFound) {
            logger.log(Level.SEVERE, userImageNotFound.getMessage());
        }

        usersRepositoryDao.addUser(user);

        resp.sendRedirect("/users-list");
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