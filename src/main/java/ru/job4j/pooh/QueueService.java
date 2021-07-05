package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> QUEUE = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {

        return null;
    }
}