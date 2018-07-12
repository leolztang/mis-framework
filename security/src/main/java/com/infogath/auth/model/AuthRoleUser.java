package com.infogath.auth.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "AUTH_ROLE_USER")
public class AuthRoleUser {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "AUTH_USER_ID")
    private BigDecimal authUserId;

    @Column(name = "AUTH_ROLE_ID")
    private BigDecimal authRoleId;

    /**
     * @return ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return AUTH_USER_ID
     */
    public BigDecimal getAuthUserId() {
        return authUserId;
    }

    /**
     * @param authUserId
     */
    public void setAuthUserId(BigDecimal authUserId) {
        this.authUserId = authUserId;
    }

    /**
     * @return AUTH_ROLE_ID
     */
    public BigDecimal getAuthRoleId() {
        return authRoleId;
    }

    /**
     * @param authRoleId
     */
    public void setAuthRoleId(BigDecimal authRoleId) {
        this.authRoleId = authRoleId;
    }
}