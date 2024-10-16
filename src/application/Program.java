package application;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        //Show data
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + "," + rs.getString("Name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    
        //Insert data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement preSt = null;
        try {
            conn = DB.getConnection();

            preSt = conn.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)");

            preSt.setString(1, "Carl Purple");
            preSt.setString(2, "carl@gmail.com");
            preSt.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            preSt.setDouble(4, 3000.0);
            preSt.setInt(5, 4);

            int rorwsAffected = preSt.executeUpdate();
            System.out.println("Done! Rows affected: " + rorwsAffected);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(preSt);
            DB.closeConnection();
        }
    
        //Uptade data
        try {
            conn = DB.getConnection();

            preSt = conn.prepareStatement(
                "UPDATE seller "
                + "SET BaseSalary = BaseSalary + ? "
                + "WHERE "
                + "(DepartmentId = ?)");
            
            preSt.setDouble(1, 200.0);
            preSt.setInt(2, 2);

            int rorwsAffected = preSt.executeUpdate();
            System.out.println("Done! Rows affected: " + rorwsAffected);
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        finally {
                DB.closeStatement(preSt);
                DB.closeConnection();
        }
        
        //Delete data
        try {
            conn = DB.getConnection();

            preSt = conn.prepareStatement(
                "DELETE FROM department "
                + "WHERE "
                + "Id = ?");
            
            preSt.setInt(1, 5);

            int rorwsAffected = preSt.executeUpdate();
            System.out.println("Done! Rows affected: " + rorwsAffected);
        }
        catch (SQLException e) {
                throw new DbException(e.getMessage());
        }
        finally {
                DB.closeStatement(preSt);
                DB.closeConnection();
        }

        //Transaction
        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("Rows 1: " + rows1);
            System.out.println("Rows 2: " + rows2);

        }
        catch (SQLException e) {
                try {
                    conn.rollback();
                    throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
                } catch (SQLException e1) {
                    throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
                }
        }
        finally {
                DB.closeStatement(preSt);
                DB.closeConnection();
        }
    }
}