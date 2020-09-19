package com.alef.urlshortning.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UrlRepository.class)

public class UrlRepositoryTest {

    private static final String URL = "test.com";

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void testCreateHash() {
        String hash = urlRepository.createShortRef(URL);
        Assert.assertNotNull(hash);
    }

    @Test
    public void testGetOriginal() {
        String hash = urlRepository.createShortRef(URL);
        String url = urlRepository.getUrl(hash);
        Assert.assertEquals(url, URL);
    }
}
