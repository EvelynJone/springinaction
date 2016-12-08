package orders.MongoDB.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Class Name : MongoConfig<BR>
 * Descripe : 启用MongoDB两个必备条件：配置MongoClient，MongoTemplate两个Bean<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/714:14<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
//EnableMongoRepositories:启用MongoDB的Repository功能
@Configuration
@EnableMongoRepositories(basePackages = "orders.MongoDB.db",repositoryImplementationPostfix = "Impl")
public class MongoConfig extends AbstractMongoConfiguration{

    /**
     * 重载getDatabaseName，mongo方法和之前配置MongoClient，MongoTemplate两个Bean的功能是相同的
     * @return
     */
    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }

    @Autowired
    private Environment env;
    @Override
    public Mongo mongo() throws Exception{
        // 创建需要认证的MongoDB服务器
/*
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                env.getProperty("mongo.username"),
                "OrdersDB",
                env.getProperty("mongo.password").toCharArray());
        new MongoClient(new ServerAddress("localhost",37017), Arrays.asList(credential));
*/

//        new MongoClient("mongodbserver",37017); 端口号不是默认的27017时需要指定端口
        return new MongoClient();
    }
//    /**
//     * MongoClient Bean
//     * @return
//     */
//    @Bean
//    public MongoFactoryBean mongo() {
//        MongoFactoryBean mongo = new MongoFactoryBean();
//        mongo.setHost("localhost");
//        return mongo;
//    }

//    /**
//     * MongoTemplate Bean
//     * @param mongo
//     * @return
//     */
//    @Bean
//    public MongoTemplate template (Mongo mongo) {
//        return new MongoTemplate(mongo,"OrdersDB");
//    }
}
