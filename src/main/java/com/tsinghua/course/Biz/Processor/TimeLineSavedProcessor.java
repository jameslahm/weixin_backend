package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.TimeLineSaved;
import com.tsinghua.course.Base.Repository.TimeLineSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeLineSavedProcessor {
    @Autowired
    TimeLineSavedRepository timeLineSavedRepository;

    public TimeLineSaved getTimeLineSavedById(String id){
        return timeLineSavedRepository.findTimeLineSavedById(id);
    }

    public TimeLineSaved saveTimeLineSaved(TimeLineSaved timeLineSaved){
        timeLineSavedRepository.save(timeLineSaved);
        return timeLineSaved;
    }

    public Message addMessageInToTimeLineSaved(String timeLineId, Message message){
        TimeLineSaved timeLineSaved = getTimeLineSavedById(timeLineId);
        timeLineSaved.getMessages().add(message);
        saveTimeLineSaved(timeLineSaved);
        return message;
    }

    public void addMessageInToTimeLineSyncs(List<String> timeLineIds, Message message){
        timeLineIds.forEach((timeLineId)->{
            addMessageInToTimeLineSaved(timeLineId,message);
        });
    }

}
