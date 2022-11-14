package pers.hd.simplepro.server.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.domain.repository.UsersRepository;
import pers.hd.simplepro.server.domain.service.UsersService;
import pers.hd.simplepro.server.domain.service.base.AbstractCrudService;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Users;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends AbstractCrudService<Users, String, UsersRepository>
        implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public UsersDTO findByUsername(String userName) {
        Users user = usersRepository.findByUserName(userName);
        if (user != null) {
            return new UsersDTO().convertFrom(user);
        }
        return null;
    }
}
