package com.spring.jdbc.data.springdata;

import java.util.List;

import com.spring.jdbc.domain.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface with operations for {@link Spitter} persistence.
 * @author habuma
 */
public interface SpitterRepository extends JpaRepository<Spitter, Long>, SpitterSweeper {
	  
	Spitter findByUsername(String username);
	
	List<Spitter> findByUsernameOrFullNameLike(String username, String fullName);

}
