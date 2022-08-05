package database.entity;

import java.util.Date;

public class Mail {
    private Integer id;
    private User from;
    private User to;
    private String subject;
    private String text;

    public Mail() {
    }

    public Mail(User from, User to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public Mail(Integer id, User from, User to, String subject, String text) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
