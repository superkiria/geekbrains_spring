package ru.motrichkin.providers;

import org.springframework.stereotype.Component;
import ru.motrichkin.contract.Claim;
import ru.motrichkin.interfaces.providers.ClaimsProvider;

import java.util.List;

@Component
public class ClaimsProviderImpl implements ClaimsProvider {

    @Override
    public void deleteClaims(Long userId) {

    }

    @Override
    public void updateClaims(Long userId, List<Claim> claims) {

    }

    @Override
    public Claim getOrCreate(Claim claim) {
        return null;
    }
}
