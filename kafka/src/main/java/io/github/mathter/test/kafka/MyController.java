package io.github.mathter.test.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
@RequestMapping("/processor")
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(
            path = "/send/{key}/{message}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> send(@PathVariable("key") String key, @PathVariable("message") String message) throws Exception {
        LOG.debug("send: {}", message);

        return Mono.fromCallable(() -> {
            this.kafkaTemplate.send("test", key, message);
            return "ok!";
        });
    }
}
