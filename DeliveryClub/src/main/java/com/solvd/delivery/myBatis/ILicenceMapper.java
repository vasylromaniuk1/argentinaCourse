package com.solvd.delivery.myBatis;

import com.solvd.delivery.DAO.classes.License;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ILicenceMapper extends IBaseMapper<License> {

  @Select(
      "SELECT * FROM Licenses where id=#{id}")

  @Results(value = {
      @Result(property = "id", column = "id"),
      @Result(property = "number", column = "number"),
      @Result(property = "expiredDate", column = "expired_date"),
      @Result(property = "employeeId", column = "employee_id")
  })

  @Override
  License getItemById(long id) throws SQLException;


  @Insert("Insert into Licenses(id,number,expired_date,employee_id) values (#{id},#{number},#{expiredDate},#{employeeId})")
  @Override
  void createItem(License item) throws SQLException;


  @Delete("Delete from Licenses where id=#{id}")
  void deleteItem(long id) throws SQLException;
}
