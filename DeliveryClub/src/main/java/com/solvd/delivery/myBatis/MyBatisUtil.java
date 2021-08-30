package com.solvd.delivery.myBatis;

import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.Utils.PropertyFileParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBatisUtil {
  public static final Logger LOGGER = LogManager.getLogger(MyBatisUtil.class);
  private static SqlSessionFactory sqlSessionFactory = null;


  private void MybatisUtil(){

  }

  public static SqlSessionFactory buildSqlSessionFactoryXml(){
    if (sqlSessionFactory == null){
      try (InputStream myBatisConfigAsStream = Resources.getResourceAsStream("mybatis-config.xml")){
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(myBatisConfigAsStream);
      } catch (IOException e) {
        LOGGER.error("Error loading mybatis config file!\n" + e);
      }
    }
    return sqlSessionFactory;
  }

  public static SqlSessionFactory buildSqlSessionFactoryJava() {
    Properties dbConnectionProperties =  PropertyFileParser.getPropertiesForFile("db.properties");
    DataSource dataSource
        = new PooledDataSource(
            dbConnectionProperties.getProperty("db.driver"), 
            dbConnectionProperties.getProperty("db.url"), 
            dbConnectionProperties.getProperty("db.username"), 
            dbConnectionProperties.getProperty("db.password")
    );

    Environment environment
        = new Environment("dev", new JdbcTransactionFactory(), dataSource);

    Configuration configuration = new Configuration(environment);
    configuration.addMapper(EmployeeMapper.class);
    configuration.addMapper(LicenceMapper.class);
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    return builder.build(configuration);
  }

}
