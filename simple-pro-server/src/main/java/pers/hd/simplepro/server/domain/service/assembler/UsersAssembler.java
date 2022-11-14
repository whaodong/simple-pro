package pers.hd.simplepro.server.domain.service.assembler;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.service.RolesService;

/**
 * @author wanghaodong
 */
@Component
public class UsersAssembler {

    private final RolesService rolesService;

    public UsersAssembler(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public Page<UsersDTO> convertToUsersDTO(Page<Users> usersPage) {
        Assert.notNull(usersPage, "Post page must not be null");

        return usersPage.map(users -> {
            UsersDTO usersDTO = new UsersDTO().convertFrom(users);

            usersDTO.setRoles(rolesService.findByUserId(users.getId()));
            return usersDTO;
        });
    }
}
