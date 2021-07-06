package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> QUEUE = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (req.method().equalsIgnoreCase("post")) {
            QUEUE.putIfAbsent(req.queueName(), new ConcurrentLinkedQueue<>());
            QUEUE.get(req.queueName()).add(req.text());
            return new Resp("Posted!", 200);
        } else if (req.method().equalsIgnoreCase("get")) {
            return new Resp(QUEUE.getOrDefault(req.queueName(), new ConcurrentLinkedQueue<String>()).poll(), 200);
        }
        return new Resp("Error!", 400);
    }
}