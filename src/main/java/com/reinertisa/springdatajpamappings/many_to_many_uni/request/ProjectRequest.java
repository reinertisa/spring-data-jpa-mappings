package com.reinertisa.springdatajpamappings.many_to_many_uni.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class ProjectRequest {
    @NotBlank
    private String projectName;
}
