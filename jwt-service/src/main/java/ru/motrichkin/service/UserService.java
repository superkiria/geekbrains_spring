package ru.motrichkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.contract.Claim;
import ru.motrichkin.contract.SystemUser;
import ru.motrichkin.interfaces.providers.IUserProvider;
import ru.motrichkin.interfaces.services.IUserService;

import java.util.List;

@Component
public class UserService implements IUserService {

    @Autowired
    private IUserProvider userProvider;

    @Override
    public List<Claim> getClaims(Long userId)  {
        return userProvider.getClaims(userId);
    }

    @Override
    public SystemUser getUser(String userName) {
        return userProvider.getUser(userName);
    }

    @Transactional
    @Override
    public boolean update(SystemUser user) {
        boolean userExist = userProvider.isUserExists(user.getUserName());
        userProvider.update(user);
        return !userExist;
    }
}
