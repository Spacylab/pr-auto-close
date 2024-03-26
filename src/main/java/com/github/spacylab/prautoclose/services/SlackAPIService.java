package com.github.spacylab.prautoclose.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.spacylab.prautoclose.dto.SlackDTO.SlackBlockDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackMessageDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackTextBlockDTO;

@Service
public class SlackAPIService {
    private String slackWebhook;
    Logger logger = Logger.getLogger(getClass().getName());

    public Object sendSlackMessage(SlackMessageDTO slackMessage) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String uriTemplate = UriComponentsBuilder.fromHttpUrl(slackWebhook).toUriString();

            ResponseEntity<String> response = restTemplate.postForEntity(uriTemplate, new HttpEntity<>(slackMessage.getAllProperties(), headers), String.class);
            return response.getBody();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public Object sendHealthMessage() {
      try {
        var textBlock = new SlackTextBlockDTO("mrkdwn", "Hello, I'm a health check message from your application.");
        var block = new SlackBlockDTO("section", textBlock);
        var message = new SlackMessageDTO();
        message.addBlock(block);

        return sendSlackMessage(message);
      } catch (Exception e) {
        logger.log(Level.SEVERE, e.getMessage());
        e.printStackTrace();
        return e.getMessage();
      }
      
    }

    public void setSlackWebhook(String slackWebhook) {
      this.slackWebhook = slackWebhook;
    }
}
