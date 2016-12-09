package Security.service;

import Security.domain.Spittle;
import org.springframework.security.access.annotation.Secured;


public class SecuredSpittleService implements SpittleService {

  @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
