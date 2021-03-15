package com.zbq.controller;

import com.zbq.domain.CentOSOneEntity;
import com.zbq.domain.SSHRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ShellControler {
    @Autowired
    CentOSOneEntity centOSOneEntity;
//    @Autowired
//    CentOSTwoEntity centOSTwoEntity;

    public int submit() throws IOException {
        // 主机1
        SSHRegisterEntity sshRegisterEntity1 = new SSHRegisterEntity(centOSOneEntity);
        // 主机2
//        SSHRegisterEntity sshRegisterEntity2 = new SSHRegisterEntity(centOSTwoEntity);
        // 向主机1发送命令
        StringBuilder stringBuilder1 = sshRegisterEntity1.exec("ll /root/");
        // 向主机2发送命令
//        StringBuilder stringBuilder2 = sshRegisterEntity2.exec("ll /root/");
        // 返回信息1
        System.out.println(stringBuilder1.toString());
        // 返回信息2
//        System.out.println(stringBuilder2.toString());
        return 1;
    }
}
