package pers.hd.simplepro.server.model.vo;

import lombok.Data;

@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}