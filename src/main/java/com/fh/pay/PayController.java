package com.fh.pay;

import com.fh.common.ServerResponse;
import com.fh.util.RedisUtil;
import com.fh.wxpay.MyConfig;
import com.fh.wxpay.WXPay;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    //生成code_url
    @RequestMapping("createNative")
    public ServerResponse payNative(String billId, BigDecimal totalPrice){
        try {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "蓬莱仙境支付");
            data.put("out_trade_no", billId);
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "123.12.12.123");
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            data.put("product_id", "12");
            //设置失效时间
            SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
            Date expireDate = DateUtils.addMinutes(new Date(), 2);
            String expireTime = sim.format(expireDate);
            data.put("time_expire", expireTime);

            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            if(!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信平台报错:" + resp.get("return_msg"));
            }
            if(!resp.get("result_code").equalsIgnoreCase("SUCCESS")){
                return ServerResponse.error("微信平台报错:" + resp.get("err_code_des"));
            }
            String code_url = resp.get("code_url");
            return ServerResponse.success(code_url);


        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error(e.getMessage());
        }

    }



    //查看支付状态
    @RequestMapping("getPayStatus")
    public ServerResponse getPayStatus(String billId){

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(3000);//设置时间
        Message message = new Message(("i am dlx message time is : "+ + System.currentTimeMillis()).getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("dlx-ex", "dlx", message);
        try {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            int count = 0;
            for (; ;) {
                Map<String, String> data = new HashMap<String, String>();
                data.put("out_trade_no", billId);

                Map<String, String> resp = wxpay.orderQuery(data);
                System.out.println(resp);

                if(!resp.get("return_code").equalsIgnoreCase("SUCCESS")){
                    return ServerResponse.error("微信平台报错:" + resp.get("return_msg"));
                }
                if(!resp.get("result_code").equalsIgnoreCase("SUCCESS")){
                    return ServerResponse.error("微信平台报错:" + resp.get("err_code_des"));
                }
                if(resp.get("trade_state").equalsIgnoreCase("SUCCESS")){
                    return ServerResponse.success();
                }
                //放入消息队列
                // 发送消息
                Thread.sleep(3000);
                count++;
                if(count > 10){
                    return ServerResponse.error("支付超时");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error(e.getMessage());
        }


    }

    //普通队列消费者
    @RabbitListener(queues = "queueA")
    @RabbitHandler
    public void receiveQueueA(Channel channel, String msg, Message message){
        System.out.println(msg);
    }

    //私信队列消费者
    @RabbitListener(queues = "ttlQueueA")
    @RabbitHandler
    public void receiveTtlQueueA(Channel channel, String msg, Message message){


    }

    @RabbitListener(queues = "dlx")
    @RabbitHandler
    public void receiveDlxQueueA(Channel channel, String msg, Message message){
        try {
            System.out.println("DLXConsumer: " + new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
