package ru.motrichkin.providers;

import org.springframework.stereotype.Component;
import ru.motrichkin.contract.Claim;
import ru.motrichkin.contract.SystemUser;
import ru.motrichkin.interfaces.providers.IUserProvider;

import java.util.List;

@Component
public class UserProviderImpl implements IUserProvider {

    private static long id = 0;

    @Override
    public SystemUser getUser(String userName) {
        SystemUser result = new SystemUser();
        result.setUserName(userName);
        result.setId(id++);
        return result;
    }

    @Override
    public boolean isUserExists(String userName) {
        return true;
    }

    @Override
    public void update(SystemUser user) {

    }

    @Override
    public List<Claim> getClaims(Long userId) {
        return null;
    }
}