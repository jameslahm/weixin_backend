package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Base.Repository.GroupRepository;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import org.graalvm.compiler.nodes.GuardPhiNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Component
public class GroupProcessor {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserProcessor userProcessor;

    public Group createGroup(String name, List<User> members ){
        // check user exists
        Group group = new Group(name,members);
        saveGroup(group);
        return group;
    }

    public boolean checkUserInGroup(User user,Group group){
        User target = group.getMembers().stream().filter((member)->{
            return member.getId()==user.getId();
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
        return group;
    }
}
