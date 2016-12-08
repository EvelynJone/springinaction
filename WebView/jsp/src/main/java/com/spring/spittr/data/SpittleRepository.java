package com.spring.spittr.data;

import com.spring.spittr.Spittle;

import java.util.List;

/**
 * Created by zhaoxl on 2016/11/28.
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);

    void save(Spittle spittle);
}
