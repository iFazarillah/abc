package com.abc.abc.service.impl;


import com.abc.abc.config.Config;
import com.abc.abc.dao.request.RegisterModel;
import com.abc.abc.model.oauth.Role;
import com.abc.abc.model.oauth.User;
import com.abc.abc.repository.oauth.RoleRepository;
import com.abc.abc.repository.oauth.UserRepository;
import com.abc.abc.service.UserService;
import com.abc.abc.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    Config config = new Config();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RoleRepository repoRole;

    @Autowired
    UserRepository repoUser;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map registerManual(RegisterModel objModel) {
        Map map = new HashMap();
        try {
            String[] roleNames = { "ROLE_USER", "ROLE_READ", "ROLE_WRITE" }; // admin
            User user = new User();
            user.setUsername(objModel.getEmail().toLowerCase());
            user.setFullname(objModel.getFullname());

            //step 1 :
//            user.setEnabled(false); // matikan user

            String password = encoder.encode(objModel.getPassword().replaceAll("\\s+", ""));
            List<Role> r = repoRole.findByNameIn(roleNames);

            user.setRoles(r);
            user.setPassword(password);
            User obj = repoUser.save(user);

            return templateResponse.templateSukses(obj);

        } catch ( Exception e ) {
            logger.error("Eror registerManual=", e);
            return templateResponse.templateError("eror:" + e);
        }

    }
}
