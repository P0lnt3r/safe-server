package org.anwang.safe.server.safescan.websocket;

import org.anwang.safe.server.safescan.context.BlockchainContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint( value =  "/socket.io/blockchain" )
@Component
public class BlockchainContextWebSocket {

    private static final Logger log = LoggerFactory.getLogger(BlockchainContextWebSocket.class);

    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) throws Exception {
        log.info("Add Session:{}" , session.getId());
        sessions.add(session);
        session.getBasicRemote().sendText(BlockchainContext.instance.serialize());
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        log.info("Remove Session:{}" , session.getId());
        sessions.remove(session);
    }

    public static void send( String message ) throws Exception{
        for( Session session : sessions ){
            session.getBasicRemote().sendText(message);
        }
    }

}
