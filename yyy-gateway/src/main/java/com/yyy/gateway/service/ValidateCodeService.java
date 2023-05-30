package com.yyy.gateway.service;

import java.io.IOException;
import com.yyy.common.core.exception.CaptchaException;
import com.yyy.common.core.web.domain.AjaxResult;

/**
 * 验证码处理
 *
* @author 羊扬杨
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
