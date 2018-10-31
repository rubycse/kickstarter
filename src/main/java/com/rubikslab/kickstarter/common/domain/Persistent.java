package com.rubikslab.kickstarter.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lutfun
 * @since 10/29/18
 */

@Data
@MappedSuperclass
public class Persistent {

    @Version
    protected int version;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date updated;
}
