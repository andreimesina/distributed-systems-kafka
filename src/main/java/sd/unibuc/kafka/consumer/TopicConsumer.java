package sd.unibuc.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TopicConsumer {

    private final Map<String, List<String>> messages = new HashMap<>();

    @KafkaListener(topicPattern = "topic*", groupId = "kafka-sandbox")
    public void listen(String topic, String message) {
        synchronized (messages) {
            List<String> topicMessages = messages.get(topic);

            if (topicMessages == null)
                topicMessages = new ArrayList();

            topicMessages.add(message);

            messages.put(topic, topicMessages);
        }
    }

    public List<String> getMessagesByTopic(String topic) {
        return messages.get(topic);
    }
}