package com.alef.urlshortning.service;

import com.alef.urlshortning.repo.UrlRepository;
import com.alef.urlshortning.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UrlServiceTest {

    private static final String HASH = "13adbcdcydc";
    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    private static final String URL ="test.com";

    @Test
    public void testCreateHash() throws UnknownHostException {
        String url =urlService.createShortUrl(URL);
        Assert.assertNotNull(url);
        Mockito.verify(urlRepository,Mockito.times(1)).createShortRef(URL);
    }

    @Test
    public void testGetRedirectUrl(){
        Mockito.when(urlRepository.getUrl(HASH)).thenReturn(URL);
        String originalUrl =urlService.getShortUrl(HASH);
        Assert.assertEquals(URL,originalUrl);

    }
}
