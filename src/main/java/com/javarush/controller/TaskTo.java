package com.javarush.controller;

import com.javarush.domain.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskTo {

    private String description;

    private Status status;

}
