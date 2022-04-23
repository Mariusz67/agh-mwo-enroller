package com.company.enroller.controllers;

import java.util.Collection;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.persistence.MeetingService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

    @Autowired
    MeetingService meetingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getMeetings() {
        Collection<Meeting> meetings = meetingService.getAll();
        return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getMeeting(@PathVariable("id") String title) {
//        Meeting meeting = meetingService.findByTitle(title);
//        if (meeting == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<?> registerMeeting(@RequestBody Meeting meeting) {
//        Meeting foundMeeting = meetingService.findByDate(meeting.getDate());
//        if (foundMeeting != null) {
//            return new ResponseEntity("Unable to create. Meeting already exists on this date " + meeting.getDate()
//                    , HttpStatus.CONFLICT);
//        }
//        meetingService.add(meeting);
//        return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//    }
//
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMeeting(@PathVariable("id") long id) {
        Meeting meeting = meetingService.findById(id);
        if (meeting == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        meetingService.delete(meeting);
        return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
    }

}
