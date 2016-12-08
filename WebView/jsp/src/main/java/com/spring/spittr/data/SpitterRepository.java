package com.spring.spittr.data;

import com.spring.spittr.Spitter;

/**
 * Created by zhaoxl on 2016/11/28.
 */
public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
