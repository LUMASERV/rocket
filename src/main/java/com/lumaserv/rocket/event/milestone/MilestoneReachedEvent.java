package com.lumaserv.rocket.event.milestone;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneReachedEvent extends Event {

    Milestone milestone;

}
