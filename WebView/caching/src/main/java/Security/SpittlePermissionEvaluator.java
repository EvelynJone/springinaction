package Security;

import Security.domain.Spittle;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.io.Serializable;

/**
 * Class Name : SpittlePermissionEvaluator<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/911:07<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */

public class SpittlePermissionEvaluator implements PermissionEvaluator {
    private static final GrantedAuthority ADMIN_AUTHORITY = new GrantedAuthorityImpl("ROLE_ADMIN");

    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if (target instanceof Spittle) {
            Spittle spittle = (Spittle) target;
            String username = spittle.getSpitter().getUsername();
            if ("delete".equals(permission)) {
                return isAdmin(authentication) || username.equals(authentication.getName());
            }
        }
        throw new UnsupportedOperationException("hasPermission not supported for object <" + target + "> and permission <"+permission+">");
    }

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
    }
}
