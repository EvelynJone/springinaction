package com.spring.jdbc.data.springdata;

import com.spring.jdbc.domain.Spittle;

import java.util.List;


public interface SpittleRepositoryCustom {

  List<Spittle> findRecent();

  List<Spittle> findRecent(int count);

}