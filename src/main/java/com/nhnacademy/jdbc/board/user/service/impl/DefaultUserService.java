package com.nhnacademy.jdbc.board.user.service.impl;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.mapper.UserMapper;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) { //원래 빨간줄 생김
        this.userMapper = userMapper;
    }

    @Override
    public boolean matches(String id, String pwd) {
        Optional<User> user = userMapper.selectUser(id);
        if(id.equals(user.get().getId()) && pwd.equals(user.get().getPassword())){
            
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdmin(String id) {
        if(Objects.isNull(id)){
            return false;
        }
        Optional<User> user = userMapper.selectUser(id);
        if(user.get().getUserSeparateCode() == 1){
            return true;
        }else{
            return false;
        }
    }


}
