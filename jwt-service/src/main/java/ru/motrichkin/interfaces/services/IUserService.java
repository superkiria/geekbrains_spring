package ru.motrichkin.interfaces.services;

import ru.motrichkin.contract.Claim;
import ru.motrichkin.contract.SystemUser;

import java.util.List;

public interface IUserService {
    /**
     * Получить данные пользователя по УЗ
     *
     * @param userName логин пользователя
     * @return {@link SystemUser}
     */
    SystemUser getUser(String userName);

    /**
     * Добавить или обновить данные пользователя
     *
     * @param user пользователь
     * @return true если был создан новый пользователь; false - если был обновлен существующий пользователь
     */
    boolean update(SystemUser user);

    /**
     * Получить права пользователя
     *
     */
    List<Claim> getClaims(Long userId);
}
