package com.rubikslab.kickstarter.user.domain;

import lombok.Getter;

import javax.persistence.Transient;

/**
 * @author lutfun
 * @since 10/29/18
 */
public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @Transient
    @Getter
    private String displayName;
}
