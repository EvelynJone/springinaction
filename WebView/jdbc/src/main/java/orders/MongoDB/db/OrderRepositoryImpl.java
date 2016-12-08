package orders.MongoDB.db;

import orders.MongoDB.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Class Name : OrderRepositoryImpl<BR>
 * Descripe : RepositoryImpl 实现了中间接口的方法，Repository再扩展这个中间接口就能把Impl的实现注入到Repository中了<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/715:18<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public class OrderRepositoryImpl implements OrderOperations {


    @Autowired
    private MongoOperations mongo;

    public List<Order> findOrdersByType(String t) {
        String type = t.equals("NET") ? "WEB" : t;
        Criteria where = Criteria.where("type").is(type);
        Query query = Query.query(where);
        return mongo.find(query,Order.class);
    }
}
