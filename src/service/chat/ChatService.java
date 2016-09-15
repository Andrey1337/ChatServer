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
import java.util.*;


@Singleton
@Path("/service")
public class ChatService {

    private Map<String, User> users;

    public ChatService(){
        this.users = new HashMap<>();
    }

    @POST
    @Path("/connect")
    public Response connect(String json) {
        try {
            User user = new Gson().fromJson(json, User.class);
            if (user.isUserValid() &&  !users.containsKey(user.getName())) {
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
        String json = gson.toJson(users);
        return json.toString();
    }
}