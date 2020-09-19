package com.alef.urlshortning.controller;

import com.alef.urlshortning.UrlController;
import com.alef.urlshortning.service.UrlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UrlController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UrlControllerTest {

    private static final String URL = "test.com";
    private static final String URL_HASH = "1cabcdser3";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;


    @Test
    public void testCreateShortUrl() throws Exception {
        Mockito.when(urlService.createShortUrl(URL)).thenReturn(URL_HASH);
        this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(URL)).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void testCreateShortUrlInException() throws Exception {
        Mockito.when(urlService.createShortUrl(URL)).thenThrow(new RuntimeException());
        this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(URL)).andExpect(status().is5xxServerError()).andDo(print());
    }
    @Test
    public void testNoEntryGetRedirectUrl() throws Exception {
        this.mockMvc.perform(get("/"+URL_HASH)).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Test
    public void testGetRedirectUrl() throws Exception {
        Mockito.when(urlService.getShortUrl(URL_HASH)).thenReturn(URL);
        this.mockMvc.perform(get("/"+URL_HASH)).andExpect(status().is3xxRedirection()).andDo(print());
    }

}
