package com.dali.security.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@Setter
public class UpdateUserOnlineStatusRequest {
    private boolean online;


}