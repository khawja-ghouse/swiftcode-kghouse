package data;

public class Message {
    public String text;
    public FeedResponse feedResponse;
    public Sender sender;
    public enum Sender{
        BOT,USER
    }
}
