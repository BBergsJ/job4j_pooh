package ru.job4j.pooh;

public class Req {
    private final String method;
    private final String mode;
    private final String text;

    private Req(String method, String mode, String text) {
        this.method = method;
        this.mode = mode;
        this.text = text;
    }

    public static Req of(String content) {
        String[] rsl = content.split("[/ \"]+");
        for (String s : rsl) {
            System.out.println(s);
        }
        return new Req(rsl[0], rsl[1], rsl[rsl.length - 1]);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }
}