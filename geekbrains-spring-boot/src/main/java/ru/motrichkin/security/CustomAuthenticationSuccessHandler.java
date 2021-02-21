package ru.motrichkin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.motrichkin.persistence.User;
import ru.motrichkin.service.UserRepresentation;
import ru.motrichkin.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String userName = authentication.getName();
        System.out.println("userName=" + userName);
        Optional<UserRepresentation> theUser = userService.findByUserName(userName);
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser.get());
        response.sendRedirect(request.getContextPath() + "/");
    }
}