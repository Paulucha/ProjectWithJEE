package com.isa.usersengine.dao;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UsersRepositoryDaoRemote {
    List<String> getUsersNames();
}
