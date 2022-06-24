package com.jingtong.platform.framework.bo;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class BasicService {
    protected void connect(String DEST) {
        SapDestinationDataProvider sapProvider = new SapDestinationDataProvider();
        try {
            if (!com.sap.conn.jco.ext.Environment.isDestinationDataProviderRegistered()) {
                com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(sapProvider);
                sapProvider.changeProperties(DEST, getDestinationPropertiesFromUI());
            }
        } catch (IllegalStateException providerAlreadyRegisteredException) {
            throw providerAlreadyRegisteredException;
        }
    }
    
    protected void connect(String DEST, int num) throws Exception {
        SapDestinationDataProvider sapProvider = new SapDestinationDataProvider();
        int connectNum = 0;
        while (connectNum < num) {
            connectNum ++;
            try {
                if (!com.sap.conn.jco.ext.Environment.isDestinationDataProviderRegistered()) {
                    com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(sapProvider);
                    sapProvider.changeProperties(DEST, getDestinationPropertiesFromUI());
                }
                return;
            } catch (IllegalStateException providerAlreadyRegisteredException) {
                if (connectNum < num) {
                    TimeUnit.SECONDS.sleep(3);
                    continue;
                }
                else {
                    throw providerAlreadyRegisteredException;
                }
            }
        }
    }

    protected JCoDestination getDest(String destName) {

        JCoDestination dest;
        try {
            dest = JCoDestinationManager.getDestination(destName);
            dest.ping();

            System.out.println("Destination " + destName + " works");
            return dest;
        } catch (JCoException e) {
            e.printStackTrace();
            System.out.println("Execution on destination " + destName + " failed");
        }

        return null;
    }

    private Properties getDestinationPropertiesFromUI() {
        PropUtil pu = new PropUtil("/sap_server.properties");
        String host = pu.getProperty("sap.host");
        String system = pu.getProperty("sap.system");
        String client = pu.getProperty("sap.client");
        String user = pu.getProperty("sap.user");
        String pwd = pu.getProperty("sap.pwd");
        String lang = pu.getProperty("sap.lang");
        /*
         * String host = "192.168.0.131"; String system = "00"; String client = "600";
         * String user = "jinshui"; String pwd = "654321"; String lang = "zh";
         */
        // adapt parameters in order to configure a valid destination
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, host);
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, system);
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, client);
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, user);
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, pwd);
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, lang);
        return connectProperties;
    }
}
