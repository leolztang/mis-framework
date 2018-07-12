package com.infogath.auth.resource;

import com.infogath.auth.service.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthPermissionResource {

    @Autowired
    AuthPermissionService authPermissionService;

}
