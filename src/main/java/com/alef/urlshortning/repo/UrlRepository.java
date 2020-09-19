package com.alef.urlshortning.repo;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UrlRepository {

    private Map<String,String> urlDatabase;

    @PostConstruct
    public void init(){
        urlDatabase =new HashMap<>();
    }

    public String createShortRef(String url){

        String md5Hex = DigestUtils
                .md5Hex(url).toUpperCase();
        urlDatabase.put(md5Hex,url);
        return md5Hex;
    }

    public String getUrl(String hash){
        return urlDatabase.get(hash);
    }
}
