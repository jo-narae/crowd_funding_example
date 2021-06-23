package com.funding.crowd.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name="FK_MEMBER_TB_FUNDING"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", foreignKey = @ForeignKey(name="FK_POST_TB_FUNDING"))
    private Post post;

    @Column(name = "funding_amount", nullable = false)
    private int fundingAmount;

    @Column
    private String comment;

    public Funding(Member member, Post post, int fundingAmount, String comment) {
        setFunding(member, post, fundingAmount, comment);
    }

    public Funding setFunding(Member member, Post post, int fundingAmount, String comment) {
        this.member = member;
        this.post = post;
        this.fundingAmount = fundingAmount;
        this.comment = comment;
        return this;
    }
}
