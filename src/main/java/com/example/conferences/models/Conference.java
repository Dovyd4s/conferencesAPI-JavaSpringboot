package com.example.conferences.models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String title;
    private String address;
    private String description;
    @Column(nullable = true)
    private LocalDate publishDate;
    @Column(nullable = true)
    private LocalDate conferenceDate;
}
