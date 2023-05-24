package com.example.conferences.repository;

import com.example.conferences.models.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository <Conference, Long> {
    List<Conference> findAllByConferenceDateAfter(LocalDate date);
    List<Conference> findAllByConferenceDateBefore(LocalDate date);
    List<Conference> findAllByConferenceDateBetween(LocalDate after, LocalDate before);
}
