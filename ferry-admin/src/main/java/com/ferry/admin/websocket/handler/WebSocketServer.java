package com.ferry.admin.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.ferry.common.mode.HandlerContext;
import com.ferry.common.utils.ContextFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：WebSocket 服务端组件
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSets = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

//    @Resource
//    private HandlerContext handlerContext;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        addOnlineCount();           //在线数加1
        logger.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        this.session = session;
        this.sid = sid;
        webSocketSets.put(this.sid, this);     //加入set中
        try {
            sendMessage("success");
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSets.remove(this.sid);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            if ("ping".equalsIgnoreCase(message)) {
                logger.info("客户端【{}】心跳检测：{}", sid, message);
                sendtoAll("pong");
            } else {
                logger.info("收到客户端【{}】的信息：{}", sid, message);
                JSONObject parse = JSONObject.parseObject(message);
                String event = parse.get("event").toString();
                String type = parse.get("type").toString();
                JSONObject eventObj = JSONObject.parseObject(event);
                String receiverId = eventObj.get("receiverId").toString();
                String senderId = eventObj.get("senderId").toString();
                HandlerContext handlerContext = (HandlerContext) ContextFactoryUtil.getBean("handlerContext");
                if (handlerContext != null) {
                    AbstractWebSocketHandler webSocketHandler = (AbstractWebSocketHandler) handlerContext.getInstance(type);
                    webSocketHandler.saveOrUpdateChatList(event);
                }
                if (receiverId == null) {
                    sendtoAll(message);
                } else {
                    sendtoUser(message, receiverId);
                    sendtoUser(message, senderId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误", error);
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
     *
     * @param message      发送消息
     * @param acceptUserId 接收人
     * @throws IOException
     */
    public void sendtoUser(String message, String acceptUserId) throws IOException {
        if (webSocketSets.get(acceptUserId) != null) {
            if (acceptUserId.equals(sid)) {
                webSocketSets.get(sid).sendMessage(message);
            } else {
                webSocketSets.get(acceptUserId).sendMessage(message);
            }
        } else {
            //如果用户不在线则返回不在线信息给自己
            sendtoUser("当前用户不在线", sid);
        }
    }

    /**
     * 发送信息给所有人
     *
     * @param message
     * @throws IOException
     */
    public void sendtoAll(String message) throws IOException {
        for (String key : webSocketSets.keySet()) {
            try {
                webSocketSets.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
