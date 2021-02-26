package ru.motrichkin.contract;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * Класс для сессии пользователя
 */
public class SessionUser extends User {

    private Long id;

    private List<Claim> claims;

    /**
     * Конструктор
     */
    public SessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * Конструктор
     */
    public SessionUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    /**
     * геттер id
     */
    public Long getId() {
        return id;
    }

    /**
     * cеттер id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * геттер claims
     */
    public List<Claim> getClaims() {
        return claims;
    }

    /**
     * cеттер claims
     */
    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}
