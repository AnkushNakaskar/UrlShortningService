package com.alef.urlshortning;

import com.alef.urlshortning.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
//@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<String> createNewUrlShort(@RequestBody String url){
        try{

            String shortUrl =urlService.createShortUrl(url);
            return new ResponseEntity<>(shortUrl,HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception in generating the short URL : "+e + " For URL : "+url);
        }
        return new ResponseEntity<>("Error...!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectAPI(@PathVariable String hash, HttpServletResponse response) throws IOException {

        try{
            if(hash!=null && !hash.isEmpty()){
                String originalUrl =urlService.getShortUrl(hash);
                if(originalUrl!=null && !originalUrl.isEmpty())
                    response.sendRedirect(originalUrl);
                else
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Exception in getting the original url reference" + hash + e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
