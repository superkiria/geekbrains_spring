package ru.motrichkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.motrichkin.contract.Claim;
import ru.motrichkin.contract.SystemUser;
import ru.motrichkin.exceptions.ApplicationException;
import ru.motrichkin.exceptions.UserNotFoundException;
import ru.motrichkin.interfaces.services.IAuthenticationService;
import ru.motrichkin.interfaces.services.IClaimsService;
import ru.motrichkin.interfaces.services.IUserService;
import ru.motrichkin.util.JwtTokenUtil;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    //    @Autowired
//    private ILdapService ldapService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IClaimsService claimsService;

    @Override
    public String getAuthToken(String userName) throws UserNotFoundException, ApplicationException {
//        SearchResult searchResult;
//        try {
//            searchResult = ldapService.findAccountByAccountName(userName);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }
//        if (searchResult == null) {
//            throw new UserNotFoundException();
//        }

        SystemUser sUser = new SystemUser();
        sUser.setUserName(userName);
        sUser.setDomainName("-");
        sUser.setDisplayName("-");
        sUser.setEmail("-");
        sUser.setGuid("-");
//        try {
//            fillAttributes(sUser, searchResult);
//        } catch (NamingException e) {
//            throw new ApplicationException(e);
//        }

        SystemUser cUser = userService.getUser(sUser.getUserName());
        boolean isNewUser = userService.update(sUser);
        if (isNewUser) {
            List<Claim> defaultClaims = claimsService.getDefaultUserClaims();
            claimsService.updateClaims(sUser.getId(), defaultClaims);
        }

        if (cUser != null) {
            sUser.setId(cUser.getId());
            sUser.setClaims(userService.getClaims(sUser.getId()));
        }

//        SystemUser sUser = new SystemUser();

//        sUser.setId(1L);
//        sUser.setDomainName("user1");
//        sUser.setUserName("user2");
//        sUser.setDisplayName("user3");
//        sUser.setEmail("mail");
//        sUser.setGuid("uuid");

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenUtil.generateToken(userDetails, sUser);
    }

    /**
     * запонение контракта из ldap
     * @param ldapUser контракт
     * @param searchResult результат поиска из Ldap
     */
    private void fillAttributes(SystemUser ldapUser, SearchResult searchResult) throws NamingException {
        Attributes attributes = searchResult.getAttributes();
        ldapUser.setUserName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDomainName(attributes.get("mailnickname").get(0).toString());
        ldapUser.setDisplayName(attributes.get("displayname").get(0).toString());
        ldapUser.setEmail(attributes.get("mail").get(0).toString());
        ldapUser.setGuid(attributes.get("objectguid").get(0).toString());
    }
}
