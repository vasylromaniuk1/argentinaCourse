package com.solvd.delivery.DAO.mysql.jdbc;


import com.solvd.delivery.DAO.DbConnection;
import com.solvd.delivery.DAO.classes.License;
import com.solvd.delivery.DAO.mysql.IBaseDAO;


import com.solvd.delivery.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

public class LincensesDAO extends AbstractMysqlJdbcDAO implements IBaseDAO<License> {
    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LincensesDAO.class);
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(LincensesDAO.class));
    private final static String GET_LICENSES_BY_ID = "SELECT * FROM Licenses WHERE id = ?";
    private final static String DB_TABLE_NAME = "mydb.Licenses";
    private final String ID_COL = "id";
    private final String NUMBER_COL = "number";
    private final String EXPIRED_DATE_COL = "expired_date";
    private final String EMPLOYEE_ID_COL = "employee_id";


    @Override
    public void createItem(License license) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        try {


            PreparedStatement ps = connection.prepareStatement("INSERT INTO "+DB_TABLE_NAME+" VALUES (?, ?, ?, ?)");

            ps.setLong(1, license.getId());
            ps.setString(2, license.getNumber());
            ps.setString(3, license.getExpiredDate());
            ps.setLong(4, license.getEmployeeId());

            int i = ps.executeUpdate();

        } catch (SQLException ex) {
            logger.error( ex.getLocalizedMessage());
        } finally {
            connection.close();
        }
    }


    @Override
    public License getItemById(long id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM "+ DB_TABLE_NAME +" WHERE id ="+id);
            ResultSet resultSet = preparedStatement.executeQuery();
            License license = new License();

            while(resultSet.next())
            {

                license = extractLicense(resultSet);
            }
            return license;

        } catch (SQLException e) {
            LOGGER.info(e.getLocalizedMessage());
        }  finally {
            connection.close();
        }
        return null;
    }


    @Override
    public boolean updateItem(License license) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {

            PreparedStatement ps = connection.prepareStatement("UPDATE "+ DB_TABLE_NAME +" SET first_name=?, last_name=?, age=? WHERE id = ?");

            ps.setString(1, license.getNumber());
            ps.setString(2, license.getExpiredDate());
            ps.setLong(3, license.getEmployeeId());
            ps.setLong(4, license.getId());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            logger.error(ex.getLocalizedMessage());
        }

        return false;
    }



    @Override
    public void deleteItem(long id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        try {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM "+DB_TABLE_NAME+" WHERE id=" + id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            logger.error( ex.getLocalizedMessage());
        }
        finally {
            connection.close();
        }

    }


    private License extractLicense (ResultSet resultSet) throws SQLException {
        License license = new License();
        license.setId(resultSet.getLong(ID_COL));
        license.setNumber(resultSet.getString(NUMBER_COL));
        license.setExpiredDate(resultSet.getString(EXPIRED_DATE_COL));
        license.setEmployeeId(resultSet.getInt(EMPLOYEE_ID_COL));
        return license;
    }
}
