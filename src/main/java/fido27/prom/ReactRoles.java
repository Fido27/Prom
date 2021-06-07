package fido27.prom;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactRoles extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        long msgID = event.getMessageIdLong();

        if (event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDC4D") && msgID == 850709159007485983L) { //TODO: Change this msgID value after going in ENoobs
            long noobID = event.getUserIdLong();
            Role role = event.getGuild().getRoleById(842394999747641364L);
            assert role != null;
            event.getGuild().addRoleToMember(noobID, role).complete();
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event){
        long msgID = event.getMessageIdLong();

        if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDC4D") && msgID == 850709159007485983L){ //TODO: Change this msgID value after going in ENoobs
            long noobID = event.getUserIdLong();
            Role role = event.getGuild().getRoleById(842394999747641364L);
            assert role != null;
            event.getGuild().removeRoleFromMember(noobID, role).complete();
        }
    }
}
