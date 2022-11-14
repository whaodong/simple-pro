package pers.hd.simplepro.server.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author WangHaoDong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO implements GrantedAuthority {
    private String authority;
}
