package model.database;

public class Constante {
    static String user="postgres";
    static String mdp="AnaTaf37";
    static String database="baovola_s5";
    public Constante() {
    }
    public static String getUser() {
        return user;
    }
    public static void setUser(String user) {
        Constante.user = user;
    }
    public static String getMdp() {
        return mdp;
    }
    public static void setMdp(String mdp) {
        Constante.mdp = mdp;
    }
    public static String getDatabase() {
        return database;
    }
    public static void setDatabase(String database) {
        Constante.database = database;
    }
}
