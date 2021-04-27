package com.tsinghua.course.Biz.Controller.Params.GroupParams.Out;

import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.ProfileOutParams;

import java.util.List;

public class GroupProfileOutParams extends CommonOutParams {
    String name;
    List<ProfileOutParams> members;

    public GroupProfileOutParams(Group group){
      this.name = group.getName();
      for (User member:group.getMembers()){
          this.members.add(new ProfileOutParams(member));
      }
    }
}
