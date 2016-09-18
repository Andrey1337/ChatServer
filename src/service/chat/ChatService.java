package service.chat;

import com.google.gson.Gson;
import com.sun.jersey.spi.resource.Singleton;
import service.chat.model.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;


@Singleton
@Path("/service")
public class ChatService {

    private Map<String, User> users;

    public ChatService() {
        this.users = new HashMap<>();
    }

    @POST
    @Path("/connect")
    public Response connect(String json) {
        try {
            User user = new Gson().fromJson(json, User.class);
            if (user.isUserValid() && !users.containsKey(user.getName())) {
                users.put(user.getName(), user);
                return Response.ok().build();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build();
    }


    @Path("/isAlive")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String isAlive() {
        return Boolean.TRUE.toString();
    }

    @Path("/getConnectedUsers")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getConnectedUsers() {
        Gson gson = new Gson();
        Collection<User> onLineUsers = users.values();
        String json = gson.toJson(onLineUsers);
        return json;
    }

  /*  @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput getServerSentEvents() {
        final EventOutput eventOutput = new EventOutput();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        // ... code that waits 1 second
                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        eventBuilder.name("message-to-client");
                        Collection<User> onLineUsers = users.values();
                        eventBuilder.data(onLineUsers);
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(
                            "Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException(
                                "Error when closing the event output.", ioClose);
                    }
                }
            }
        }).start();
        return eventOutput;
    }*/
}