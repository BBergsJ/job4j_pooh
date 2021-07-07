package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final static ConcurrentHashMap<
            String,
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>
                    >> TOPIC = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (req.method().equalsIgnoreCase("post")) {
            TOPIC.putIfAbsent(req.queueName(), new ConcurrentHashMap<>() {{
                put(req.userId(), new ConcurrentLinkedQueue<>());
            }});
            TOPIC.get(req.queueName()).get(req.userId()).add(req.text());
            return new Resp("Posted " + req.queueName(), 200);
        } else if (req.method().equalsIgnoreCase("get")) {
            return TOPIC.containsKey(req.queueName())
                    ? new Resp(TOPIC.get(req.queueName()).get(req.userId()).poll(), 200)
                    : new Resp("", 404);
        }
        return new Resp("Error!", 400);
    }
}