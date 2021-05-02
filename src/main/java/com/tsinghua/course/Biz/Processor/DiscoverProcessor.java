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

    public Discover createDiscover(User sender, String content,
                                   List<String> images,String video, long timestamp){
        Discover discover = new Discover(sender,content,images,video,timestamp);
        saveDiscover(discover);
        return discover;
    }

    public Discover getDiscoverById(String discoverId) {
        return discoverRepository.findDiscoverById(discoverId);
    }

    public List<Discover> getDiscoversByFriends(User user){
        List<Friend> friends = user.getFriends();
        List<String> friendIds = friends.stream().map((friend -> friend.getId())).collect(Collectors.toList());
        friendIds.add(user.getId());
        List<Discover> discovers = discoverRepository.findDiscoversBySenderInOrderByTimestampDesc(friendIds);
        return discovers;
    }

    public Discover likeDiscover(User user,Discover discover){
        discover.getLikesBy().add(user);
        saveDiscover(discover);
        return discover;
    }

    public Discover unLikeDiscover(User target,Discover discover){
        discover.getLikesBy().removeIf((user)->{
            return user.getId().equals(target.getId());
        });
        saveDiscover(discover);
        return discover;
    }

    public Discover replyDiscover(User user,Discover discover,String content,long timestamp){
        discover.getReplies().add(new Reply(user,content,timestamp));
        saveDiscover(discover);
        return discover;
    }
}
