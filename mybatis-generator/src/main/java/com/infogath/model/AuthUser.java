package com.infogath.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "AUTH_USER")
public class AuthUser {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Short enabled;

    @Column(name = "LOCKED")
    private Short locked;

    @Column(name = "CREATE_TIMESTAMP")
    private Date createTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private Date updateTimestamp;

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
     * @return LOGIN_ID
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return ENABLED
     */
    public Short getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    /**
     * @return LOCKED
     */
    public Short getLocked() {
        return locked;
    }

    /**
     * @param locked
     */
    public void setLocked(Short locked) {
        this.locked = locked;
    }

    /**
     * @return CREATE_TIMESTAMP
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @param createTimestamp
     */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * @return UPDATE_TIMESTAMP
     */
    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * @param updateTimestamp
     */
    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}