/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author TAFITA
 */
public class TimeStamp implements Serializable {
    Timestamp dateTime;
    int jour;
    String mois;
    int annee;
    String heure;
    String minute;
    String seconde="00";

    public TimeStamp(Timestamp dateTime)
    throws Exception {
        this.setDateTime(dateTime);
        this.setJour(dateTime.toLocalDateTime().getDayOfMonth());
        this.setAnnee(dateTime.toLocalDateTime().getYear());
        this.setHeure(String.valueOf(dateTime.toLocalDateTime().getHour()));
        this.setMinute(String.valueOf(dateTime.toLocalDateTime().getMinute()));
        this.setSeconde(String.valueOf(dateTime.toLocalDateTime().getSecond()));
    }

    public TimeStamp(String ddn, String hdn)
    throws Exception {
        hdn+=":00";
        this.setDateTime(Timestamp.valueOf(ddn+" "+hdn));
        this.setJour(dateTime.toLocalDateTime().getDayOfMonth());
        this.setAnnee(dateTime.toLocalDateTime().getYear());
        this.setHeure(String.valueOf(dateTime.toLocalDateTime().getHour()));
        this.setMinute(String.valueOf(dateTime.toLocalDateTime().getMinute()));
        this.setSeconde(String.valueOf(dateTime.toLocalDateTime().getSecond()));
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime)
    throws Exception {
        this.dateTime = dateTime;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour)
    throws Exception {
        this.jour = jour;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois)
    throws Exception {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee)
    throws Exception {
        this.annee = annee;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure)
    throws Exception {
        this.heure = heure;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) 
    throws Exception {
        this.minute = minute;
    }

    public String getSeconde() {
        return seconde;
    }

    public void setSeconde(String seconde)
    throws Exception {
        this.seconde = seconde;
    }

    public TimeStamp() {
    }

    public TimeStamp(Timestamp dateTime, int jour, String mois, int annee, String heure, String minute, String seconde)
    throws Exception {
        this.setDateTime(dateTime);
        this.setJour(jour);
        this.setMois(mois);
        this.setAnnee(annee);
        this.setHeure(heure);
        this.setMinute(minute);
        this.setSeconde(seconde);
    }
    
    public String getStringDate() {
        if(this.getMois()==null) {
            return "Date non definie";
        }
        return this.getJour()+"-"+this.getMois()+"-"+this.getAnnee();
    }

    public String getStringNumberDate() {
        if(this.getDateTime()==null) {
            return "Date non definie";
        }
        return this.getAnnee()+"-"+this.getDateTime().toLocalDateTime().getMonth().getValue()+"-"+this.getJour();
    }
    
    public String getStringHeure() {
        return this.getHeure()+":"+this.getMinute()+":"+this.getSeconde();
    }

    public String getAge() {
        return LocalDate.now().getYear()-this.getAnnee()+" ans";
    }

    public String oracleToTimestamp() {
        return "to_timestamp('"+this.getStringNumberDate()+" "+this.getStringHeure()+"', 'YYYY-MM-DD HH24:MI:SS')";
    }

    public int compareTo(TimeStamp timestamp) 
    throws Exception {
        return this.getDateTime().compareTo(timestamp.getDateTime());
    }
}
