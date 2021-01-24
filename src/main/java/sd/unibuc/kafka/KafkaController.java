package sd.unibuc.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sd.unibuc.kafka.consumer.TopicConsumer;

import java.util.List;

@RestController
public class KafkaController {

    private KafkaTemplate<String, String> template;
    private TopicConsumer topicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, TopicConsumer topicConsumer) {
        this.template = template;
        this.topicConsumer = topicConsumer;
    }

    @PostMapping("/kafka/produce")
    public void produce(@RequestParam String topic, @RequestParam String message) {
        template.send(topic, message);
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessagesByTopic(String topic) {
        return topicConsumer.getMessagesByTopic(topic);
    }
}