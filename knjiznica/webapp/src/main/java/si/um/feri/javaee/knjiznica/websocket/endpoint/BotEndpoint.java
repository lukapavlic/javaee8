package si.um.feri.javaee.knjiznica.websocket.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import si.um.feri.javaee.knjiznica.websocket.bean.BotBean;
import si.um.feri.javaee.knjiznica.websocket.model.decoders.MessageDecoder;
import si.um.feri.javaee.knjiznica.websocket.model.encoders.ChatMessageEncoder;
import si.um.feri.javaee.knjiznica.websocket.model.encoders.InfoMessageEncoder;
import si.um.feri.javaee.knjiznica.websocket.model.encoders.JoinMessageEncoder;
import si.um.feri.javaee.knjiznica.websocket.model.encoders.UsersMessageEncoder;
import si.um.feri.javaee.knjiznica.websocket.model.messages.ChatMessage;
import si.um.feri.javaee.knjiznica.websocket.model.messages.InfoMessage;
import si.um.feri.javaee.knjiznica.websocket.model.messages.JoinMessage;
import si.um.feri.javaee.knjiznica.websocket.model.messages.Message;
import si.um.feri.javaee.knjiznica.websocket.model.messages.UsersMessage;

/**
 * Končna točka WebSocketa
 * Lahko imamo več kodirnikov
 * Lahko imamo največ enega dekodirnika
 */
@ServerEndpoint(
        value = "/websocketbot",
        decoders = { MessageDecoder.class }, 
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class, 
                     InfoMessageEncoder.class, UsersMessageEncoder.class }
        )
/* There is a BotEndpoint instance per connetion */
public class BotEndpoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    /* Bot functionality bean */
    @Inject
    private BotBean botbean;
    /* Executor service for asynchronous processing */
    /* lookup = "java:comp" for WildFly */
    //@Resource (lookup="java:jboss/ee/concurrency/executor/default")
    
    private ManagedExecutorService mes; 
    
    public BotEndpoint() throws NamingException {
		// TODO Auto-generated constructor stub
    	mes = (ManagedExecutorService) InitialContext.doLookup("java:jboss/ee/concurrency/executor/default");
	}
    
    @OnOpen
    public void openConnection(Session session) {
        logger.log(Level.INFO, "Connection opened.");
    }
    
    /**
     * Ob sprejetju sporočila preveri kakšen tip je sporočilo in ustrezno ravnamo
     * @param session
     * @param msg
     */
    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        
        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getName());
            session.getUserProperties().put("active", true);
            logger.log(Level.INFO, "Received: {0}", jmsg.toString());
            sendAll(session, new InfoMessage(jmsg.getName() + 
                    " se je prijavil v klepetalnico"));
            sendAll(session, new ChatMessage("Janez", jmsg.getName(),
                    "Pozdravljen!! Jaz sem Janez. Kako lahko pomagam?"));
            sendAll(session, new UsersMessage(this.getUserList(session)));
            
        } else if (msg instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage cmsg = (ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());
            sendAll(session, cmsg);
            System.out.println("Target: " + cmsg.getTarget());
            if (cmsg.getTarget().compareTo("Janez") == 0) {
                /* The bot replies to the message */
                mes.submit(new Runnable() {
                    @Override
                    public void run() {
                        String resp = botbean.respond(cmsg.getMessage());
                        sendAll(session, new ChatMessage("Janez", 
                                cmsg.getName(), resp));
                    }
                });
            }
        }
    }
    
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session, new InfoMessage(name + " je odšel"));
            sendAll(session, new UsersMessage(this.getUserList(session)));
        }
        logger.log(Level.INFO, "Connection closed.");
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }
    
    /** Pošiljanje sporočila vsem končnim uporabnikom (odjemalcem)
     * 	Na osnovi tipa sporočila končna točka avtomatsko ugotovi katerega kodirnika mora uporabiti 
     */
    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }   
    }
    
    /** Vrne seznam uporabnikov preko vseh odprtih povezav
     * 	getOpenSessions()
     */
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        users.add("Janez");
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
