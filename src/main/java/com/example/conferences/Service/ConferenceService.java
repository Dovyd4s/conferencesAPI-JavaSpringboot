package com.example.conferences.Service;

import com.example.conferences.models.Conference;
import com.example.conferences.repository.ConferenceRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Service
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public List<Conference> getConferences() {
        return conferenceRepository.findAll();
    }

    public void addConference(Conference conference) {
        conference.setPublishDate(LocalDate.now());
        conferenceRepository.save(conference);
        System.out.println(conference);
    }

    public void deleteConference(Long id) {
        if(!conferenceRepository.existsById(id)){
            throw new IllegalStateException("No conference with such ID");
        }else{
            conferenceRepository.deleteById(id);
        }

    }
    @Transactional
    public void updateConference(Long id, String updateInfo) {
        System.out.println(id );
        Conference conference = conferenceRepository.findById(id).orElseThrow(()->
                new IllegalStateException("No conference with such ID"));
        Gson gson = new Gson();
        Properties properties = gson.fromJson(updateInfo, Properties.class);

        if(properties.getProperty("title")!=null){
            conference.setTitle(properties.getProperty("title"));
        }if(properties.getProperty("address")!=null){
            conference.setAddress(properties.getProperty("address"));
        }if(properties.getProperty("description")!=null){
            conference.setDescription(properties.getProperty("description"));
        }if (properties.getProperty("conferenceDate")!=null){
            conference.setConferenceDate(LocalDate.parse(properties.getProperty("conferenceDate")));
        }
    }

    public Conference getConferenceById(Long id) {
        return conferenceRepository.findById(id).orElseThrow(()->
                new IllegalStateException("No conference with such ID"));
    }

    public List<Conference> getConferencesBeforeDate(LocalDate toDate) {
        return conferenceRepository.findAllByConferenceDateBefore(toDate.plusDays(1));
    }

    public List<Conference> getConferencesAfterDate(LocalDate fromDate) {
        return conferenceRepository.findAllByConferenceDateAfter(fromDate.minusDays(1));
    }

    public List<Conference> getConferencesBetweenDates(LocalDate fromDate, LocalDate toDate) {
        return conferenceRepository.findAllByConferenceDateBetween(fromDate.minusDays(1), toDate.plusDays(1));
    }
}
