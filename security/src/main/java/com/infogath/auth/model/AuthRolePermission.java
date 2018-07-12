package com.infogath.auth.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "AUTH_ROLE_PERMISSION")
public class AuthRolePermission {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "AUTH_ROLE_ID")
    private BigDecimal authRoleId;

    @Column(name = "AUTH_PERMISSION_ID")
    private BigDecimal authPermissionId;

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

    /**
     * @return AUTH_PERMISSION_ID
     */
    public BigDecimal getAuthPermissionId() {
        return authPermissionId;
    }

    /**
     * @param authPermissionId
     */
    public void setAuthPermissionId(BigDecimal authPermissionId) {
        this.authPermissionId = authPermissionId;
    }
}