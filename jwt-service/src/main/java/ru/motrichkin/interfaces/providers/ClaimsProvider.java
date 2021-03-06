package ru.motrichkin.interfaces.providers;

import ru.motrichkin.contract.Claim;

import java.util.List;

public interface ClaimsProvider {

    /**
     * Удалить права пользователя
     * @param userId id пользователя
     */
    void deleteClaims(Long userId);

    /**
     * Удалить текущие права пользователя и сохранить новые
     *
     * @param userId id пользователя
     * @param claims права пользователя
     */
    void updateClaims(Long userId, List<Claim> claims);

    /**
     * Получить из базы существующий клейм или создать новый
     *
     * @param claim клейм
     * @return клейм с идентификатором
     */
    Claim getOrCreate(Claim claim);
}

