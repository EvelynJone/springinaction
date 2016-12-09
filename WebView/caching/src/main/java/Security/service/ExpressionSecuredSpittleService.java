package Security.service;

import Security.domain.Spittle;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;


public class ExpressionSecuredSpittleService implements SpittleService {

  @PostAuthorize("returnObject.spitter.username == principal.username")
  @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_PREMIUM')")
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
