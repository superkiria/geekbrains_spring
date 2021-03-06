package ru.motrichkin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.contract.Claim;
import ru.motrichkin.interfaces.providers.ClaimsProvider;
import ru.motrichkin.interfaces.services.IClaimsService;
import ru.motrichkin.util.VmaClaimTypes;
import ru.motrichkin.util.VmaRoles;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ClaimsServiceImpl implements IClaimsService {

    private final ClaimsProvider claimsProvider;

    public ClaimsServiceImpl(ClaimsProvider claimsProvider) {
        this.claimsProvider = claimsProvider;
    }

    @Transactional
    @Override
    public void updateClaims(Long userId, List<Claim> claims) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(claims);
        claimsProvider.deleteClaims(userId);
        claimsProvider.updateClaims(userId, claims);
    }

    @Override
    public List<Claim> getDefaultUserClaims() {
        Claim claim = new Claim(VmaClaimTypes.ROLES, VmaRoles.DOMAIN_USER);
        Claim claimWithId = claimsProvider.getOrCreate(claim);
        return Collections.singletonList(claimWithId);
    }

}
