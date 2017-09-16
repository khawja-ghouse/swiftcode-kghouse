package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import services.Agentservice;
import services.FeedService;

import java.util.Objects;


public class MessageActor extends UntypedActor
{

    public static Props props(ActorRef out)
    {
        return Props.create(MessageActor.class, out);
    }

    private final ActorRef out;
    private FeedService feedService = new FeedService();
    private Agentservice agentService = new Agentservice();

    public MessageActor(ActorRef out)
    {
        this.out = out;
    }

    public void onReceive(Object message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message messageObject = new Message();
        if (message instanceof String) {
            messageObject.text = (String) message;
            messageObject.sender = Message.Sender.USER;
            out.tell(mapper.writeValueAsString(messageObject),
                    self());
            String keyword = agentService.getAgentResponse((String) message).keyword;

            if (!Objects.equals(keyword, "NOT_FOUND")) {
                FeedResponse feedResponse = feedService.getFeedResponse(keyword);
                messageObject.text = (feedResponse.title == null) ? "No results found" : "Showing results for: " + keyword;
                messageObject.feedResponse = feedResponse;
                messageObject.sender = Message.Sender.BOT;
                out.tell(mapper.writeValueAsString(messageObject), self());
            }
        }
    }
}