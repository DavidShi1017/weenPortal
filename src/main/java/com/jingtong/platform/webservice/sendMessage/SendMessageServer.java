package com.jingtong.platform.webservice.sendMessage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.webservice.pojo.SendMessage;
import com.jingtong.platform.webservice.resps.MD5andKL;

public class SendMessageServer {
    private static String sendMessageWsdlUrl;
    private static String vCode;
    private static String moblieNumbers;
    static {
        PropUtil pu = new PropUtil("/sap_server.properties");
        sendMessageWsdlUrl = pu.getProperty("send.sendMessageWsdlUrl");
        vCode = MD5andKL.MD5(pu.getProperty("send.vCode").trim());
        moblieNumbers = pu.getProperty("send.mobileNumber");
    }

    public static void sendMessage(String str) {
        try {
            String[] numbers = moblieNumbers.split(",");
            List<SendMessage> list = new ArrayList<SendMessage>();
            if (numbers != null) {
                for (int i = 0; i < numbers.length; i++) {
                    SendMessage message = new SendMessage();
                    message.setMobileNumber(numbers[i].toString());
                    message.setMessageContent("接口：" + str + "数据同步有失败记录,请及时查看。");
                    message.setMessageContent(str);
                    list.add(message);
                }
            }
            SendMessageServer.excuteClientSendMessage(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 短信发送到翼讯接口
     */
    public static String excuteClientSendMessage(List<SendMessage> sendMessageList) throws Exception {
        String reInfo = "0"; // 0表示成功 1表示失败
        if (sendMessageList == null || sendMessageList.size() == 0) {
            throw new Exception("没有要发送的短信");
        }
        String mobileNumber = "";
        for (SendMessage sendMessage : sendMessageList) {
            mobileNumber = mobileNumber + sendMessage.getMobileNumber() + ",";
        }
        String message = sendMessageList.get(0).getMessageContent();
        String messageJson = "{\"vcode\":\"" + vCode + "\",\"mobile\":\"" + mobileNumber + "\",\"message\":\"" + message
                + "\"}";
        try {
            String packageName = "http://service.lanju.com/";
            String method = "sendSms";
            Object[] objects = executeSendMessageClient(sendMessageWsdlUrl, packageName, method, messageJson);
            String json = objects[0].toString();
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return reInfo;
    }

    private static Object[] executeSendMessageClient(String sendMessageWsdlUrl, String packageName, String method,
            String vCode) throws Exception {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient(sendMessageWsdlUrl);

        QName name = new QName(packageName, method);
        Object[] objects = client.invoke(name, vCode);
        if (objects == null || objects.length <= 0) {
            throw new Exception("调用接口返回null");
        }
        return objects;
    }
}
