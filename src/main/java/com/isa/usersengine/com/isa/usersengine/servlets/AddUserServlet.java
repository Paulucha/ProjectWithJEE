package com.isa.usersengine.com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {

    private UsersRepositoryDao usersRepositoryDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usersRepositoryDao = new UsersRepositoryDaoBean();
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