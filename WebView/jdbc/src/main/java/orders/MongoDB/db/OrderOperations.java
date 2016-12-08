package orders.MongoDB.db;

import orders.MongoDB.Order;

import java.util.List;

/**
 * Class Name : OrderOperations<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/715:17<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public interface OrderOperations {
    List<Order> findOrdersByType(String t);
}
