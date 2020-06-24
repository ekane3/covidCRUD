package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/bd";//Variable de connexion a la BD
    private String username = "root";
    private String password = "";
    PreparedStatement pst;

    public Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver : " + ex.getMessage());
        }
        try {
            con = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException ex) {
            System.out.println("Connexion : " + ex.getMessage());
        }
    }

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver : " + ex.getMessage());
        }
        try {
            con = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException ex) {
            System.out.println("Connexion : " + ex.getMessage());
        }
    }

    public ResultSet prepare(String statement, ArrayList params) {
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(statement);
            if (!params.isEmpty()) {
                for (int i = 0; i < params.size(); i++) {
                    pst.setString(i + 1, params.get(i).toString());
                }
            }
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet executeQuery(String statement) {
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(statement);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public int executeUpdate(String statement) {
        ResultSet rs = null;
        int rst = 0;
        try {
            pst = con.prepareStatement(statement,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rst = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("executeupdate : " + e.getMessage());
        }
        return rst;
    }
    
    // aficher toutes les info d'une table
    public ResultSet querySelectAll(String table) {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + table;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    
    //methode pour afficher toutes les tables sous une contraintes
    public ResultSet querySelectAll(String table, String columnDB, String etat) {
        ResultSet rs = null;
        String sql = "SELECT * FROM " + table + " WHERE " + columnDB + " LIKE '%" + etat + "%'";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Ajouter dans la Bd
    

  


    

}
