package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.TimeLineSaved;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Base.Repository.GroupRepository;
import com.tsinghua.course.Base.Repository.TimeLineSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupProcessor {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TimeLineSavedRepository timeLineSavedRepository;

    @Autowired
    UserProcessor userProcessor;

    public Group createGroup(String name, List<User> members ){
        // check user exists
        TimeLineSaved timeLineSaved = new TimeLineSaved();
        timeLineSavedRepository.save(timeLineSaved);
        Group group = new Group(name,timeLineSaved.getId() ,new ArrayList<>(members));
        saveGroup(group);
        return group;
    }

    public boolean checkUserInGroup(User user,Group group){
        User target = group.getMembers().stream().filter((member)->{
            return member.getId().equals(user.getId());
        }).findFirst().orElse(null);
        if(target==null){
            return false;
        } else {
            return true;
        }
    }

    public Group getGroupById(String groupId){
        return groupRepository.findGroupById(groupId);
    }

    public Group saveGroup(Group group){
        groupRepository.save(group);
        return group;
    }

    public Group addMemberIntoGroup(User user, Group group){
        group.getMembers().add(user);
        saveGroup(group);
        return group;
    }

    public Group deleteMemberInGroup(User user,Group group){
        group.getMembers().removeIf((member)->{
            return member.getId().equals(user.getId());
        });
        saveGroup(group);
        return group;
    }
}
