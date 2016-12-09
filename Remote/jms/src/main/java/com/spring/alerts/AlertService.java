package com.spring.alerts;

import com.spring.domain.Spittle;

/**
 * Class Name : AlertService<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/917:06<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public interface AlertService {
    void sendSpittleAlert(Spittle spittle);

    Spittle receiveSpittleAlert();
}
