package orders.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

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
@EnableNeo4jRepositories(basePackages = "orders.MongoDB.db",repositoryImplementationPostfix = "Impl")
public class Neo4jConfig extends Neo4jConfiguration{


    @Override
    public SessionFactory getSessionFactory() {
        return null;
    }

    public Neo4jConfig() {

    }

//    public Graph
}
