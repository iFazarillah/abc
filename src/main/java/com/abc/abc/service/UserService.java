package com.abc.abc.service;

import com.abc.abc.dao.request.RegisterModel;

import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel);
}
