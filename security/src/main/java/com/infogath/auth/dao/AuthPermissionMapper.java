package com.infogath.auth.dao;

import com.infogath.auth.model.AuthPermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuthPermissionMapper extends Mapper<AuthPermission> {

    List<AuthPermission> findByUserId(int userId);
}