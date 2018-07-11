package com.isa.usersengine.dao;

import com.isa.usersengine.domain.User;
import com.isa.usersengine.repository.UsersRepository;
import javax.ejb.Stateless;


import java.util.List;

@Stateless
public class UsersRepositoryDaoBean implements UsersRepositoryDao {

    @Override
    public void addUser(User user) {
        UsersRepository.getRepository().add(user);

    }
    @Override
    public User getUserById(final Integer id) {
        return UsersRepository.getRepository().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
    @Override
    public User getUserByLogin(String login) {
        return UsersRepository.getRepository().stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }
    @Override
    public List<User> getUsersList() {
        return UsersRepository.getRepository();
    }
}
