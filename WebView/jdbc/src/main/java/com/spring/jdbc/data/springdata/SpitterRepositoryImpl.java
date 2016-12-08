package com.spring.jdbc.data.springdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Class Name : SpitterRepositoryImpl<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/711:11<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public class SpitterRepositoryImpl implements SpitterSweeper {

    @PersistenceContext
    private EntityManager entityManager;

    public int eliteSweep() {
        String update = "UPDATE Spitter s " +
                "SET s.status = 'Elite' " +
                "WHERE s.status = 'Newbie' "+
                "AND s.id IN (" +
                "SELECT t FROM Spitter t WHERE (" +
                " SELECT count(spittles) FROM t.spittles sl) > 10000 " +
                ") ";
        return entityManager.createQuery(update).executeUpdate();
    }
}
