package pers.hd.simplepro.server.domain.service;

import pers.hd.simplepro.server.domain.repository.UsersRepository;
import pers.hd.simplepro.server.domain.service.base.BaseCrudService;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Users;

/**
 * @author WangHaoDong
 */
public interface UsersService extends BaseCrudService<Users, String, UsersRepository> {
    UsersDTO findByUsername(String userName);

}
