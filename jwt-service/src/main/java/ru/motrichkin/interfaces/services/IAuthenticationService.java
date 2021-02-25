package ru.motrichkin.interfaces.services;

import ru.motrichkin.exceptions.ApplicationException;
import ru.motrichkin.exceptions.UserNotFoundException;

import javax.naming.NamingException;

public interface IAuthenticationService {

    /**
     * Получить аутентификационный токен для пользователя
     * @param userName имя пользователя
     * @return токен
     */
    String getAuthToken(String userName) throws UserNotFoundException, NamingException, ApplicationException;
}
