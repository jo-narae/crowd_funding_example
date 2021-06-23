package com.funding.crowd.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "target_amount", nullable = false)
    private int targetAmount;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @Column(name = "participating_members", nullable = false)
    private int participatingMembers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<Funding> fundings;

    public Post setPost(String title, String description, int targetAmount, Timestamp startDate, Timestamp endDate,
                           int totalAmount, int participatingMembers) {
        this.title = title;
        this.description = description;
        this.targetAmount = targetAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.participatingMembers = participatingMembers;

        return this;
    }
}
