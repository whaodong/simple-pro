package pers.hd.simplepro.server.domain.model.vo;

import lombok.Data;

@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}