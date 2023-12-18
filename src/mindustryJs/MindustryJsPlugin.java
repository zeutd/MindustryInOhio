package mindustryJs;

import arc.util.*;
import mindustry.gen.*;
import mindustry.mod.*;

import static mindustry.Vars.mods;

public class MindustryJsPlugin extends Plugin{

    //called when game initializes
    @Override
    public void init(){
        //listen for a block selection event
        /*Events.on(BuildSelectEvent.class, event -> {
            if(!event.breaking && event.builder != null && event.builder.buildPlan() != null && event.builder.buildPlan().block == Blocks.thoriumReactor && event.builder.isPlayer()){
                //player is the unit controller
                Player player = event.builder.getPlayer();

                //send a message to everyone saying that this player has begun building a reactor
                Call.sendMessage("[scarlet]ALERT![] " + player.name + " has begun building a reactor at " + event.tile.x + ", " + event.tile.y);
            }
        });*/

        //add a chat filter that changes the contents of all messages
        //in this case, all instances of "heck" are censored
        /*Vars.netServer.admins.addChatFilter((player, text) -> text.replace("heck", "h*ck"));

        //add an action filter for preventing players from doing certain things
        Vars.netServer.admins.addActionFilter(action -> {
            //random example: prevent blast compound depositing
            if(action.type == ActionType.depositItem && action.item == Items.blastCompound && action.tile.block() instanceof CoreBlock){
                action.player.sendMessage("Example action filter: Prevents players from depositing blast compound into the core.");
                return false;
            }
            return true;
        });*/
    }

    //register commands that run on the server
    @Override
    public void registerServerCommands(CommandHandler handler){

    }

    //register commands that player can invoke in-game
    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("js", "<text...>", "runs a js console in the server", (args, player) -> {
            StringBuilder str = new StringBuilder();
            for (String arg : args) {
                str.append(arg).append(" ");
            }
            Call.sendMessage("[yellow][[JS][white] " + str);
            player.sendMessage("[yellow][[JS][white] [[" + player.name + "[white]] " + mods.getScripts().runConsole(str.toString()).replace("[", "[["));
        });
    }
}
