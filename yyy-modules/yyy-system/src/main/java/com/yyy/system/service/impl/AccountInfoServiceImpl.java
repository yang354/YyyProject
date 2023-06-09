package com.yyy.system.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.yyy.system.mapper.AccountInfoMapper;
import com.yyy.system.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoMapper accountInfoDao;



    @DS("bank1")
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {

        //扣减张三的金额
        accountInfoDao.updateAccountBalance(accountNo,amount *-1);

    }
}
