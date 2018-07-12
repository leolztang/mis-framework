package com.infogath.auth.service.impl;

import com.infogath.auth.dao.AuthPermissionMapper;
import com.infogath.auth.model.AuthPermission;
import com.infogath.auth.service.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Autowired
    AuthPermissionMapper authPermissionMapper;

    public List<AuthPermission> findByAdminUserId(Integer userId) {
        return authPermissionMapper.findByUserId(userId);
    }
}
