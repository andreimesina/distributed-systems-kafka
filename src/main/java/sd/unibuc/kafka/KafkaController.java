package sd.unibuc.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
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
    public void produce(@RequestBody MessageRequest messageRequest) {
        template.send(messageRequest.getTopic(), messageRequest.getMessage());
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessagesByTopic(String topic) {
        return topicConsumer.getMessagesByTopic(topic);
    }
}