package ru.job4j.pooh;

/**
 * Response model
 * @author Dmitry Emelyanov
 * @version 1.0
 * @since 24.06.21
 */
public class Resp {
    private final String text;
    private final int status;

    public Resp(String text, int status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public int status() {
        return status;
    }
}