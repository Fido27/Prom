package fido27.prom;

import java.sql.*;

public class SQLite {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement prepstmt = null;

    SQLite(){
        //try to connect to db
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MemberMonitor.sqlite");
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listMembers(){
        try{
            this.stmt = conn.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM MemberActivityMonitor");
            while(rs.next()){
                int id = rs.getInt("MemberID");
                String name = rs.getString("MemberName");
                int points = rs.getInt("Points");
                System.out.println(id + "\t" + name + "\t" + points);
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void closeConn(){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public ResultSet exeQuery(String query){
        try{
            this.stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void exeUpdate(String query){
        try{
            this.stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void exePrepQuery(String query){
        try{
            this.prepstmt = conn.prepareStatement(query);
            prepstmt.executeQuery(query);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}