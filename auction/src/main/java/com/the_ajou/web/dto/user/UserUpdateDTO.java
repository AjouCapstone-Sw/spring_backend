package com.the_ajou.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateDTO {
    private String phoneNum;
    private String address;
    private String nickname;
    private char gender;
    private String birth;
}
