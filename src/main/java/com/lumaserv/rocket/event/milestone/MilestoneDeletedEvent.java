package com.lumaserv.rocket.event.milestone;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MilestoneDeletedEvent extends Event {

    Milestone milestone;

}
