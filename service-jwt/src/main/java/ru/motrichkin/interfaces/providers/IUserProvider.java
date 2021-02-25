package ru.motrichkin.interfaces.providers;

import ru.motrichkin.contract.Claim;
import ru.motrichkin.contract.SystemUser;

import java.util.List;

public interface IUserProvider {
    /**
     * Получить данные пользователя по УЗ
     *
     * @param userName имя пользователя
     * @return {@link SystemUser}
     */
    SystemUser getUser(String userName);

    /**
     * Проверить сущесвтует ли пользователь в базе
     * @param userName имя пользователя
     * @return true - сущесвтует; false - не существует
     */
    boolean isUserExists(String userName);

    /**
     * Добавить или обновить данные пользователя вместе с ролями
     *
     */
    void update(SystemUser user);

    /**
     * Получить права пользователя
     *
     */
    List<Claim> getClaims(Long userId);
}
