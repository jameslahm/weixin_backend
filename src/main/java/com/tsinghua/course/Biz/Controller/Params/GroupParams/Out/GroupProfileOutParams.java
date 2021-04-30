package com.tsinghua.course.Biz.Controller.Params.GroupParams.Out;

import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.ProfileOutParams;

import java.util.List;

public class GroupProfileOutParams extends CommonOutParams {
    String name;

    String id;
    String avatar;

    String timeLineSaved;

    List<ProfileOutParams> members;

    public GroupProfileOutParams(Group group){
      this.name = group.getName();
      this.avatar = group.getAvatar();
      this.id = group.getId();
      this.timeLineSaved = group.getTimeLineSavedId();
      for (User member:group.getMembers()){
          this.members.add(new ProfileOutParams(member));
      }
    }
}
