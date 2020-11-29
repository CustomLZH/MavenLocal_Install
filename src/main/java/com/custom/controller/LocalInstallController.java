package com.custom.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/local")
public class LocalInstallController {

    @RequestMapping("/getCode")
    public Map<String, Object> getCode(@RequestBody Map<String,String> info){
        Map<String, Object> map = new HashMap<>();
        try {
            String mavenStr = info.get("mavenStr");
            String fileUrl = info.get("fileUrl");
            String groupId = mavenStr.substring(mavenStr.indexOf("<groupId>") + "<groupId>".length(), mavenStr.indexOf("</groupId>"));
            String artifactId = mavenStr.substring(mavenStr.indexOf("<artifactId>") + "<artifactId>".length(), mavenStr.indexOf("</artifactId>"));
            String version = mavenStr.substring(mavenStr.indexOf("<version>") + "<version>".length(), mavenStr.indexOf("</version>"));

            String data = "mvn install:install-file -Dfile=" + fileUrl + " -DgroupId=" + groupId + " -DartifactId=" + artifactId + " -Dversion=" + version + " -Dpackaging=jar";

            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("data", data);
            return map;
        }catch (Exception e){
            map.put("flag", false);
            map.put("message", "获取失败");
            map.put("data", "");
            return map;
        }
    }
}