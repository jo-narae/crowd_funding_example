package com.funding.crowd.dto;

import com.funding.crowd.domain.Funding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Builder
public class FundingDTO {

    @NotEmpty
    @NotBlank
    private Long memberId;

    @NotEmpty
    @NotBlank
    private Long postId;

    @NotEmpty
    @NotBlank
    private int fundingAmount;

    @NotEmpty
    @NotBlank
    private String comment;
}
