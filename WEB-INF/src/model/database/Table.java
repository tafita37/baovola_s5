package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Table {
    public static String selectNextVal(Connection con, String sequenceName, String prefixe) throws Exception {
        String result="";
        boolean jAiOuvert=false;
        if(con==null) {
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            jAiOuvert=true;
        }
        Statement stat=null;
        ResultSet res=null;
        try {
            stat=con.createStatement();
            String sql="select nextval('"+sequenceName+"')";
            // System.out.println(sql);
            res=stat.executeQuery(sql);
            while(res.next()) {
                result=res.getString("nextval");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            stat.close();
            if(jAiOuvert) {
                con.close();
            }
        }
        return prefixe+result;
    }

    public static int countAllFromTab(Connection con, String tableName) 
    throws Exception {
        int result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select count(*) as nb from "+tableName;
            preparedStatement = con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=resultSet.getInt("nb");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            preparedStatement.close();
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public static int countSql(Connection con, String sql)
    throws Exception {
        int result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql1=sql.replace("*", ",").split(",")[0];
            String sql2=sql.replace("*", ",").split(",")[1];
            sql=sql1+" count(*) as nb "+sql2;
            System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=resultSet.getInt("nb");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            preparedStatement.close();
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }
}
