package ru.motrichkin.interfaces.services;

import ru.motrichkin.contract.Claim;

import java.util.List;

public interface IClaimsService {

    /**
     * Установить пользователю новые права, при этом старые права удаляются
     *
     * @param userId id пользователя
     * @param claims новые права
     */
    void updateClaims(Long userId, List<Claim> claims);

    /**
     * Получить список дефолтных прав для нового пользователя
     *
     * @return возвращает список прав с идентификаторами
     */
    List<Claim> getDefaultUserClaims();
}
