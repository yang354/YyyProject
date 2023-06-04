package com.yyy.common.sms.service;

/**
 * @Author yzz
 * @Date 2023/5/30 下午6:36
 */
public interface SmsService {
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    boolean sendVerificationCode(String phone);

    /**
     * 验证验证码
     * @param phone
     * @param code
     * @return
     */
    Object verificationCode(String phone, String code);

    String verificationCode_b(String phone, String code);
}
