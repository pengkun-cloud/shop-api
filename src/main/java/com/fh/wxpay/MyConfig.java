package com.fh.wxpay;

import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class MyConfig extends WXPayConfig {
    @Override
    String getAppID() {
        return "wxa1e44e130a9a8eee";
    }

    @Override
    String getMchID() {
        return "1507758211";
    }

    @Override
    String getKey() {
        return "feihujiaoyu12345678yuxiaoyang123";
    }

    @Override
    InputStream getCertStream() {
        return null;
    }


    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API,true);
            }
        };
    }
}
