package com.ferry.gossip.service;

import com.ferry.server.blog.entity.Gossip;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 摆渡人
 * @Date: 2021/6/11
 */
public interface GossipService {

    public List <Gossip> findAll();

    public Gossip findById(String id);

    public void save(Gossip gossip);

    public void update(Gossip gossip);

    public void deleteById(String id);

    public Page <Gossip> pageQuery(String parentid, int page, int size);

    public Page <Gossip> pageByUser(int page, int size);

    public void addthumbup(String id);

    public List<Gossip> findAllByPre(String gossipId);
}
