package com.frabef.httppoolclient.rs;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyResponse {

    private String status;
    private String message;
    private Date start;
    private Date end;

}
