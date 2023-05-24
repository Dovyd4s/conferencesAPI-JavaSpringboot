package com.example.conferences.controllers;

import com.example.conferences.Service.ConferenceService;
import com.example.conferences.models.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "conferences")
public class ConferenceController {
    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService){
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public List<Conference> getConferences(
            @RequestParam(required = false, name = "fromDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fromDate,
            @RequestParam(required = false, name = "toDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate)
            {
        if (fromDate != null && toDate != null) {
            return conferenceService.getConferencesBetweenDates(fromDate, toDate);
        } else if (fromDate != null) {
            return conferenceService.getConferencesAfterDate(fromDate);
        } else if (toDate != null) {
            return conferenceService.getConferencesBeforeDate(toDate);
        } else {
            return conferenceService.getConferences();
        }
    }

    @GetMapping(path = "/{id}")
    public Conference getConferenceById(@PathVariable("id")Long id){
        return conferenceService.getConferenceById(id);
    }

    @PostMapping(path = "/create")
    public void addConference (@RequestBody Conference conference){
        conferenceService.addConference(conference);
    }

    @PutMapping(path = "/update/{id}")
    public void updateConference (
            @PathVariable("id")Long id,
            @RequestBody String updateInfo){
        System.out.println("first: " + updateInfo);
        conferenceService.updateConference(id, updateInfo);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteConference (@PathVariable("id") Long id){
        conferenceService.deleteConference(id);
    }
}