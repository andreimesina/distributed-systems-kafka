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

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/kafka/produce")
    public void produce(@RequestBody MessageRequest messageRequest) {
        System.out.println(messageRequest.getTopic() + " " + messageRequest.getMessage());
        template.send(messageRequest.getTopic(), messageRequest.getMessage());
    }

//    @GetMapping("/kafka/messages")
//    public List<String> getMessagesByTopic(@RequestBody String topic) {
//        return topicConsumer.getMessagesByTopic(topic);
//    }

    @GetMapping("/kafka/messages2")
    public List<String> getMessages() {
        return topicConsumer.getMessages();
    }
}