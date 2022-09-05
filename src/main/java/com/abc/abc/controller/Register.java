package com.abc.abc.controller;

import com.abc.abc.config.Config;
import com.abc.abc.dao.request.RegisterModel;
import com.abc.abc.model.oauth.User;
import com.abc.abc.repository.oauth.UserRepository;
import com.abc.abc.service.UserService;
import com.abc.abc.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-register/")
public class Register {
    @Autowired
    private UserRepository userRepository;

    Config config = new Config();

    @Autowired
    public UserService serviceReq;

    @Autowired
    public TemplateResponse templateCRUD;

    @PostMapping("/register")
    public ResponseEntity<Map> saveRegisterManual(@RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = userRepository.checkExistingEmail(objModel.getEmail());
        if ( null != user ) {
            return new ResponseEntity<Map>(templateCRUD.notFound("Username sudah ada"), HttpStatus.OK);

        }
        map = serviceReq.registerManual(objModel);


        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}
