package com.zbq.domain;

import com.zbq.common.CentOSEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix="linuxNote")
public class CentOSOneEntity extends CentOSEntity {
    /* 主机（IP） */
    private String host;
    /* 连接端口 */
    private int port;
    /* 编码 */
    private Charset charset = StandardCharsets.UTF_8;
    /* 用户 */
    private String user;
    /* 密码 */
    private String password;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
