import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class Mailer {



    public static void main (String[] args){
        String csvFile = args[0];
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] arg_csv = line.split(cvsSplitBy);
                System.out.println("Prénom/nom : " + arg_csv[0] + " , Date :" + arg_csv[1]);
                String[] date_employee = arg_csv[1].split("/");
                String email = arg_csv[0];
                DateFormat dateFormat = new SimpleDateFormat("dd/MM");
                Date date = new Date();
                String[] date_today = dateFormat.format(date).split("/");

                int today_day = Integer.parseInt(date_today[0]);
                int today_month = Integer.parseInt(date_today[1]);
                int personne_day = Integer.parseInt(date_employee[0]);
                int personne_month = Integer.parseInt(date_employee[1]);

                if (today_day == personne_day && today_month == personne_month) {
                    MailerService.sendMail("Bon Anniversaire","AAYYYYAAAAA",email);
                    System.out.println(" Email envoyé");
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
