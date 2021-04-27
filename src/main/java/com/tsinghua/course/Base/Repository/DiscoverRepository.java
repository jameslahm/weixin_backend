package com.tsinghua.course.Base.Repository;

import com.tsinghua.course.Base.Model.Discover;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiscoverRepository extends MongoRepository<Discover,String> {
    public Discover findDiscoverById(String id);

    public List<Discover> findDiscoversBySenderInOrderByTimestampDesc(List<String> senders);
}
