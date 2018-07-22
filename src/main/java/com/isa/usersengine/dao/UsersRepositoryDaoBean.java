package com.isa.usersengine.dao;

import com.isa.usersengine.domain.User;
import com.isa.usersengine.interceptors.AddUserInterceptor;
import com.isa.usersengine.interceptors.AddUserSetGenderInterceptor;
import com.isa.usersengine.repository.UsersRepository;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;


import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UsersRepositoryDaoBean implements UsersRepositoryDao, UsersRepositoryDaoRemote {

    @Override
    @Interceptors({AddUserInterceptor.class, AddUserSetGenderInterceptor.class})
    public void addUser(User user) {
        UsersRepository.getRepository().add(user);
    }

    @Override
    public User getUserById(Integer id) {
        return UsersRepository.getRepository().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User getUserByLogin(String login) {
        return UsersRepository.getRepository().stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }

    @Override
    public List<User> getUsersList() {
        return UsersRepository.getRepository();
    }

    @Override
    public List<String> getUsersNames() {
        return UsersRepository.getRepository().stream().map(u -> u.getName()).collect(Collectors.toList());
    }
}