package com.rubikslab.kickstarter.user.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Transient;

/**
 * @author lutfun
 * @since 10/29/18
 */
public enum Role {
    ROLE_ADMIN("Admin"), ROLE_USER("User");

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Transient
    @Getter
    private String displayName;

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.toString());
    }
}
