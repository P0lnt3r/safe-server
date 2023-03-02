package org.anwang.safe.server.monitor.scheduler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VpnServerPortCheckScheduler implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(VpnServerPortCheckScheduler.class);

    List<VpnServerPort> vpnServerPortList;

    @Value("${vpn.file-path}")
    String vpnFilePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        loadVpn();
        checkVpn();
    }

    private void loadVpn(){
        log.info("加载 vpn 文件:{}" , vpnFilePath);
        String vpnJSON = FileUtil.readString(vpnFilePath, "UTF-8");
        JSONArray arr = JSONUtil.parseArray(vpnJSON);
        vpnServerPortList = arr.stream().map( str -> {
            String[] vpn = str.toString().split(":");
            String ip = vpn[0];
            Integer port = Integer.parseInt(vpn[1]);
            return new VpnServerPortCheckScheduler.VpnServerPort(ip , port);
        }).collect(Collectors.toList());
        log.info("完成加载,VPN服务信息:{}" , JSONUtil.toJsonPrettyStr(vpnServerPortList));
    }

    private void checkVpn(){
        List<VpnServerPort> errorVpnServerPortList = new ArrayList<>();
        vpnServerPortList.forEach( vpnServerPort -> {
            String ip = vpnServerPort.getIp();
            Integer port = vpnServerPort.getPort();
            log.info("检查Vpn服务[{}:{}]" ,ip , port);
            boolean checkResult = check(ip , port);
            int retry = 0;
            if ( !checkResult ){
                while( !checkResult && retry < 2 ){
                    log.info("第{}次 重试检查VPN服务[{}:{}]" , ++retry , ip , port);
                    checkResult = check(ip , port);
                }
                if ( !checkResult ){
                    errorVpnServerPortList.add(vpnServerPort);
                }
            }
        });
        if ( ! errorVpnServerPortList.isEmpty() ){
            log.info("存在无法连接的VPN服务数量:{},需要进行Email通知." , errorVpnServerPortList.size());
            String content = JSONUtil.toJsonPrettyStr(errorVpnServerPortList);
            log.info("故障VPN服务节点:{}" , content);
            String subject = "VPN服务异常:正常["+(vpnServerPortList.size() - errorVpnServerPortList.size())+"],异常["+errorVpnServerPortList.size()+"]";
            String response = MailUtil.sendText(
                    "739988293@qq.com,262756885@qq.com",
                    subject,
                    content,
                    null
            );
            log.info("调用 Email 通知 , Response = {}" , response);
        }else{
            log.info("Vpn 服务全部正常...");
        }
    }

    @Scheduled( cron = "0 0/30 * * * ?" )
    public void loop() {
        loadVpn();
        checkVpn();
    }

    private boolean check(String ip, Integer port) {
        Socket socket = new Socket();
        boolean isConnected = false;
        try {
            long startTime = System.currentTimeMillis();
            socket.connect(
                    new InetSocketAddress(ip , port),
                    10000
            );
            long cost = System.currentTimeMillis() - startTime;
            isConnected = socket.isConnected();
            log.info("Vpn服务[{}:{}]连接正常({}ms)" , ip , port, cost);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Vpn服务[{}:{}]不可用!" , ip , port );
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isConnected;
    }

    public static class VpnServerPort {

        public VpnServerPort() {
        }

        public VpnServerPort(String ip, Integer port) {
            this.ip = ip;
            this.port = port;
        }

        private String ip;

        private Integer port;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}
