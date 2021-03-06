package ru.job4j.pooh;

/**
 * Request model
 * @author Dmitry Emelyanov
 * @version 1.0
 * @since 24.06.21
 */
public class Req {
    private final String method;
    private final String mode;
    private final String queueName;
    private final String userId;
    private final String text;

    private Req(String method, String mode, String queueName, String userId, String text) {
        this.method = method;
        this.mode = mode;
        this.queueName = queueName;
        this.userId = userId;
        this.text = text;
    }

    /**
     * Request parser
     * @param content - a request to the server in string format
     * @return Req - complete request object
     */
    public static Req of(String content) {
        String[] rsl = content.split("[/ \"%]+");
        return new Req(rsl[0], rsl[1], rsl[2], rsl[3], rsl[rsl.length - 1]);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String queueName() {
        return queueName;
    }

    public String userId() {
        return userId;
    }

    public String text() {
        return text;
    }
}