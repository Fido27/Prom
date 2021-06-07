package fido27.prom;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.sql.ResultSet;

public class Commands extends ListenerAdapter{

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

        //This is the Ping Pong Code
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!ping")){
            event.getChannel().sendMessage("Pong!").queue();
        }

        //This is the shutdown code
        if (event.getMessage().getContentRaw().equalsIgnoreCase( Prom.prefix + "stop") && event.getAuthor().getId().equalsIgnoreCase(Config.get("OWNER_ID"))){
            event.getJDA().shutdown(); //TODO: Actually test this out and then remove the print line
            System.out.println("Bot Exit Success");
        }

        //These are the common variables
        String[] words = event.getMessage().getContentRaw().split("\\s+");
        String author = event.getAuthor().getName();
        long longID = event.getMessage().getAuthor().getIdLong();
        int noOfWords = words.length;
        SQLite db = new SQLite();

        //This is the Database Code
        if (!event.getAuthor().isBot()){ //TODO: Check if this makes a new entry for nicknames or not
            if (noOfWords >= 15) {
                ResultSet rs = db.exeQuery("Select * from MemberActivityMonitor WHERE MemberName = '" + author + "'");
                try {
                    if(rs.next()){
                        db.exeUpdate("UPDATE MemberActivityMonitor SET Points = Points+600 WHERE MemberName = '" + author + "'"); //TODO: Change 600 to 1
                    }else{
                        db.exeUpdate("INSERT INTO MemberActivityMonitor (MemberID, MemberName, Points) VALUES ('" + longID + "', '" + author + "',0)");
                    }
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        //This is the code to update roles when user reaches 500 point. Type "prom UpdateRoles"
        if (words.length > 1){ //TODO: Make it so that only ENoobs can use this command and add if first word is prom
            if(words[0].equalsIgnoreCase(Prom.prefix) && words[1].equalsIgnoreCase("UpdateRoles")) { //TODO: Add prefix
                ResultSet rs = db.exeQuery("SELECT MemberID FROM MemberActivityMonitor WHERE Points > 500");
                try {
                    long noobID = rs.getLong("MemberID");
                    Role role = event.getGuild().getRoleById(842394999747641364L); //TODO: Change this value once prom enters ENoobs
                    while (rs.next()) {
                        assert role != null;
                        event.getGuild().addRoleToMember(noobID, role).complete();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

}
