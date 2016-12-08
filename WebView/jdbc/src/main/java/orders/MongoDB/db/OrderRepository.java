package orders.MongoDB.db;

import orders.MongoDB.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Class Name : OrderRepository<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/715:01<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public interface OrderRepository extends MongoRepository<Order,String>,OrderOperations {

    List<Order> findByCustomer(String c);
    int countByCustomer(String c);
    Order findASingleOrderByCustomer(String c);

    List<Order> findByCustomerLike(String c);
    List<Order> findByCustomerAndType(String c, String t) ;
    List<Order> findByCustomerLikeAndType(String c, String t);

    @Query("{'customer':'Chuck Wagon','type': ?0}")
    List<Order> findChucksOrders(String t);
    @Query("{'customer':'Chuck Wagon'}")
    List<Order> findChucksOrders();
}
