package com.example.service.serviceImpl;

import com.example.vo.PasswordVo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Author:Sphinx
 * Date:2019/03/27 11:57
 * Description:
 */

@Service
@PropertySource("classpath:config/encrypt.properties")
public class PasswordServiceImpl implements PasswordService {
    //@Autowired
    //private Environment environment;
    @Value("${shiro.algorithmName}")
    private String algorithmName;
    @Value("${shiro.hashIterations}")
    private Integer hashIterations;

    @Override
    public String encryptPassword(Object o) throws IllegalArgumentException {
        PasswordVo passwordVo=(PasswordVo) o;
        return new SimpleHash(algorithmName,passwordVo.getPassword(),passwordVo.getSalt(),hashIterations).toBase64();
    }

    @Override
    public boolean passwordsMatch(Object o, String s) {
        return false;
    }
}
