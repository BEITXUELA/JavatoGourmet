/*
 * Esta clase implementa metodos especializados en tareas relacionadas con 
 * fechas y tiempos
 */
package control.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo C. Araujo
 */
public class TimeMachine {

    /**
     * Este metodo devuelve el el TimeStamp actual, util para "fechas de alta" o
     * "fechas de modificacion"
     *
     * @return Timestamp Marca de tiempo
     */
    public static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    /**
     * Este metodo devuelve un TimeStamp proveniente de BBDD a formato Date en
     * Java
     *
     * @param ts El TimeStamp de BBDD
     * @return Date El valor Date formateado
     */
    public static Date getTimeStampFromDB(java.sql.Timestamp ts) {
        DateFormat formatter;
        Date date = null;
        String tsString;

        try {
            if (ts != null)  {
                tsString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(ts);
                formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                date = (Date) formatter.parse(tsString);
                return date;
            }
        } catch (ParseException ex) {
            Logger.getLogger(TimeMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
