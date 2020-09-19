package com.alef.urlshortning.service;

import com.alef.urlshortning.repo.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class UrlService {

    @Value("${server.servlet.context-path}")
    private String baseUrl;

    @Value("${server.port}")
    private String port;


    @Autowired
    private UrlRepository urlRepository;

    public String createShortUrl(String url) throws UnknownHostException {
        if (url == null || url.isEmpty()) {
            return null;
        }
        InetAddress localhost = InetAddress.getLocalHost();
        String localIp =(localhost.getHostAddress()).trim();
        return localIp +":"+ port+baseUrl+"/"+urlRepository.createShortRef(url);

    }

    public String getShortUrl(String hash) {
        return urlRepository.getUrl(hash);
    }
}
