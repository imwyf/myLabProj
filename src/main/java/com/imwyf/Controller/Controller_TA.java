package com.imwyf.Controller;

import com.imwyf.entity.TA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

@Controller
public class Controller_TA {

    public static final String TA_ATTRIBUTES_PATH = "conf/TA_attributes.txt";
    public static int TA_LISTEN_PORT = 9090;
    private final ServerSocket serverSocket;
    private final TA ta;

    public Controller_TA() throws IOException {
        serverSocket = new ServerSocket(TA_LISTEN_PORT);
        ta = new TA();
        String attr = SetAttributes();
        System.out.println("全局属性集合为: [" + attr + "]");
        System.out.println("TA初始化完成");
        System.out.println();
    }

    @RequestMapping("/init_TA")
    @ResponseBody
    public String init_TA() throws IOException {
        System.out.println("TA初始化完成");
        return "TA初始化完成";
    }

    /**
     * 设置TA的全局属性池，从conf文件中读取
     * @throws IOException
     */
    public String SetAttributes() throws IOException {
        // 读取全局属性集合
        Set<String> attributes = new HashSet<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(TA_ATTRIBUTES_PATH)));
        String attr = in.readLine();

        for (String s: attr.split(" ")) {
            attributes.add(s);
        }
        in.close();
        ta.setUp(attributes);
        return attr;
    }

}

