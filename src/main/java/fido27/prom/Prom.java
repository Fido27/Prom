package fido27.prom;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Prom {
    public static String prefix = Config.get("PREFIX");

    public static void main(String[] args) throws LoginException {
        //Some space for actual code things.







        //Token to get the bot to start.
        JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).addEventListeners().build();
        //To set current online activity wali cheez.
        jda.getPresence().setActivity(Activity.playing("With your MOM"));

        //Following is to add all the new Classes.
        jda.addEventListener(new Commands());
        jda.addEventListener(new ReactRoles());

        //Following are all the Database things.
        //String addMember = "INSERT INTO MemberActivityMonitor (MemberName, Points) VALUES (" + ;

        //Following line is to list the Database in console.
        //db.listMembers();
        //Following line is to close the Database.
        //db.closeConn();
        //db.exeQuery(addMember);
    }

}
