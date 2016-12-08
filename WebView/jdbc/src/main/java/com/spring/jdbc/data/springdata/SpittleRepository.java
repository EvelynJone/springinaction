package com.spring.jdbc.data.springdata;

import java.util.List;

import com.spring.jdbc.domain.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface with operations for {@link Spittle} persistence.
 * @author habuma
 */
public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleRepositoryCustom {
  
  List<Spittle> findBySpitterId(long spitterId);
  
}
