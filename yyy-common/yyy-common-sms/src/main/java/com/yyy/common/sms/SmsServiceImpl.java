package com.yyy.common.sms;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.yyy.common.core.domain.R;
import com.yyy.common.core.utils.NumberUtils;
import com.yyy.common.redis.service.RedisService;
import com.yyy.common.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.yyy.common.core.constant.SmsConstants.SMS_PHONE_VERIFICATION_CODE;
import static com.yyy.common.core.constant.SmsConstants.SMS_PHONE_VERIFICATION_CODE_COUNT;

/**
 * @Author yzz
 * @Date 2023/5/30 下午6:36
 */
@Component
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${tencent.SecretId}")
    private String SecretId;

    @Value("${tencent.SecretKey}")
    private String SecretKey;

    @Value("${tencent.sms.SdkAppId}")
    private String AppID;

    @Value("${tencent.sms.SignName}")
    private String SignName;

    @Value("${tencent.sms.TemplateID}")
    private String TemplateID;


    @Autowired
    private RedisService redisUtil;


    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Override
    public boolean sendVerificationCode(String phone) {
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey，见《创建secretId和secretKey》小节
        Credential cred = new Credential(SecretId,
                SecretKey);
        // 实例化要请求产品(以cvm为例)的client对象
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
        SmsClient smsClient = new SmsClient(cred, null);//第二个ap-chongqing 填产品所在的区
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setSmsSdkAppid(AppID);//appId ,见《创建应用》小节
        String[] phones={"+86"+phone};  //发送短信的目标手机号，可填多个。
        sendSmsRequest.setPhoneNumberSet(phones);
        //sendSmsRequest.setTemplateID("497248");  //模版id,见《创建短信签名和模版》小节
        sendSmsRequest.setTemplateID(TemplateID);
        String verificationCode=String.valueOf(NumberUtils.buildRandom(6));
        log.info("手机号码:{},验证码{}",phone,verificationCode);
        redisUtil.setCacheObject(SMS_PHONE_VERIFICATION_CODE + phone, verificationCode,60l*10, TimeUnit.SECONDS);
        if(redisUtil.hasKey(SMS_PHONE_VERIFICATION_CODE_COUNT + phone)){
            String count=redisUtil.getCacheObject(SMS_PHONE_VERIFICATION_CODE_COUNT + phone);
            if("6".equals(count)){
                //return new SendSmsResponse();
                return false;
            }else{
                redisUtil.incrBy(SMS_PHONE_VERIFICATION_CODE + phone,Long.valueOf(count));
            }
        }
        String [] templateParam={verificationCode,"10"};//模版参数，从前往后对应的是模版的{1}、{2}等,见《创建短信签名和模版》小节
        /*
        短信中的参数，使用的模板是“您的注册验证码为{1}，有效期{2}分钟，如非本人操作，请忽略本短信。”，所以填入两个参数
        【链橘科技】验证码为：{}，您正在进行机构激活操作，请在{}分钟内填写，若非本人操作，请勿泄露。
         */
        sendSmsRequest.setTemplateParamSet(templateParam);
        sendSmsRequest.setSign(SignName); //签名内容，不是填签名id,见《创建短信签名和模版》小节 自己腾讯云创建签名时填写的签名内容
        try {
            SendSmsResponse sendSmsResponse= smsClient.SendSms(sendSmsRequest); //发送短信
            log.info("发送短信,返回信息:{}",sendSmsResponse);
            //return sendSmsResponse;
            return true;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        //return new SendSmsResponse();
        return false;
    }



    @Override
    public R verificationCode(String phone, String code) {
        if(StringUtils.isBlank(code)){
            return R.fail("手机号码不能为空!");
        }else if(StringUtils.isBlank(phone)){
            return R.fail("验证码不能为空!");
        }
        String key="sms:phone:verification_code:" + phone;
        if(redisUtil.hasKey(key)){
            String redisCode=redisUtil.getCacheObject(key);
            System.out.printf("code:"+redisCode );
            if(code.equals(redisCode)){
                redisUtil.deleteObject(key);
                return R.ok(code,"验证成功!");
            }else{
                return R.fail("验证码错误!");
            }
        }else{
            return R.fail("验证码已过期!");
        }
    }

    @Override
    public String verificationCode_b(String phone, String code) {
        if(StringUtils.isBlank(code)){
            return "手机号码不能为空!";
        }else if(StringUtils.isBlank(phone)){
            return "验证码不能为空!";
        }
        String key="sms:phone:verification_code:" + phone;
        if(redisUtil.hasKey(key)){
            String redisCode=redisUtil.getCacheObject(key);
            System.out.printf("code:"+redisCode );
            if(code.equals(redisCode)){
                redisUtil.deleteObject(key);
                return "ok";
            }else{
                return "验证码错误!";
            }
        }else{
            return "验证码已过期!";
        }
    }
}
