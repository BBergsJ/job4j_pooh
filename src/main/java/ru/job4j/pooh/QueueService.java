package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * This class handles queue mode
 * @author Dmitry Emelyanov
 * @version 1.0
 * @since 24.06.21
 */
public class QueueService implements Service {
    /**
     *ConcurrentHashMap contains a queue of messages
     */
    private final static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> QUEUE = new ConcurrentHashMap<>();

    /**
     * Handles request and returns a response
     * @param req - request object
     * @return Resp - response object
     */
    @Override
    public Resp process(Req req) {
        if (req.method().equalsIgnoreCase("post")) {
            QUEUE.putIfAbsent(req.queueName(), new ConcurrentLinkedQueue<>());
            QUEUE.get(req.queueName()).add(req.text());
            return new Resp("Posted " + req.queueName(), 200);
        } else if (req.method().equalsIgnoreCase("get")) {
            return QUEUE.containsKey(req.queueName())
                    ? new Resp(QUEUE.get(req.queueName()).poll(), 200)
                    : new Resp("", 404);
        }
        return new Resp("Error!", 400);
    }
}