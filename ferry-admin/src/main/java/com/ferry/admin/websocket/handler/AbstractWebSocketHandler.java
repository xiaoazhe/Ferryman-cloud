package com.ferry.admin.websocket.handler;


import com.ferry.common.mode.IModeHandleService;

/**
 * 描述：websocket 抽象处理器
 */
public abstract class AbstractWebSocketHandler implements IModeHandleService {
    public abstract boolean saveOrUpdateChatList(String event);
}
