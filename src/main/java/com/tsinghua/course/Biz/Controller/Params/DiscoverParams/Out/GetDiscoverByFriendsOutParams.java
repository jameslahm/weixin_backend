package com.tsinghua.course.Biz.Controller.Params.DiscoverParams.Out;

import com.tsinghua.course.Base.Model.Discover;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;
import java.util.stream.Collectors;

public class GetDiscoverByFriendsOutParams extends CommonOutParams {
    List<DiscoverProfileOutParams> discovers;

    public GetDiscoverByFriendsOutParams(List<Discover> discovers){
        this.discovers = discovers.stream().map(discover -> {
            return new DiscoverProfileOutParams(discover);
        }).collect(Collectors.toList());
    }

    public List<DiscoverProfileOutParams> getDiscovers() {
        return discovers;
    }

    public void setDiscovers(List<DiscoverProfileOutParams> discovers) {
        this.discovers = discovers;
    }
}
