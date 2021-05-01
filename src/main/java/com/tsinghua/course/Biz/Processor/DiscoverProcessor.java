package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Discover;
import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.Reply;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Base.Repository.DiscoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscoverProcessor {
    @Autowired
    DiscoverRepository discoverRepository;

    public Discover saveDiscover(Discover discover){
        discoverRepository.save(discover);
        return discover;
    }

    public Discover createDiscover(String sender, String content,
                                   List<String> images, int timestamp){
        Discover discover = new Discover(sender,content,images,timestamp);
        saveDiscover(discover);
        return discover;
    }

    public Discover getDiscoverById(String discoverId) {
        return discoverRepository.findDiscoverById(discoverId);
    }

    public List<Discover> getDiscoversByFriends(User user){
        List<Friend> friends = user.getFriends();
        List<String> friendIds = friends.stream().map((friend -> friend.getId())).collect(Collectors.toList());
        List<Discover> discovers = discoverRepository.findDiscoversBySenderInOrderByTimestampDesc(friendIds);
        return discovers;
    }

    public Discover likeDiscover(User user,Discover discover){
        discover.getLikesBy().add(user.getId());
        saveDiscover(discover);
        return discover;
    }

    public Discover unLikeDiscover(User user,Discover discover){
        discover.getLikesBy().removeIf((id)->{
            return id.equals(user.getId());
        });
        saveDiscover(discover);
        return discover;
    }

    public Discover replyDiscover(User user,Discover discover,String content,int timestamp){
        discover.getReplies().add(new Reply(user.getId(),content,timestamp));
        saveDiscover(discover);
        return discover;
    }
}
