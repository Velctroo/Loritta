package com.mrpowergamerbr.loritta.commands.vanilla.social

import com.mrpowergamerbr.loritta.LorittaLauncher
import com.mrpowergamerbr.loritta.commands.CommandBase
import com.mrpowergamerbr.loritta.commands.CommandCategory
import com.mrpowergamerbr.loritta.commands.CommandContext
import com.mrpowergamerbr.loritta.userdata.LorittaProfile

class GamesCommand : CommandBase() {
    override fun getLabel():String {
        return "games";
    }

    override fun getDescription(): String {
        return "Veja os jogos mais jogados pela galera!";
    }

    override fun getCategory(): CommandCategory {
         return CommandCategory.SOCIAL;
    }

    override fun run(context: CommandContext) {
        val dbObjects = LorittaLauncher.getInstance().mongo.getDatabase("loritta").getCollection("users").find();
        val profiles = ArrayList<LorittaProfile>();
        val list = HashMap<String, Long>();

        for (dbObject in dbObjects) {
            profiles.add(LorittaLauncher.getInstance().ds.get(LorittaProfile::class.java, dbObject.get("_id")));
        }

        for (profile in profiles) {
            for (entry in profile.games) {
                list.put(entry.key, 1 + list.getOrDefault(entry.key, 0L));
            }
        }

        var entries = list.entries.toList();
        entries = entries.sortedWith(compareBy({ it.value })).reversed();

        var str = "";

        var idx = 0;
        for ((game, timesPlayed) in entries) {
            if (idx > 9) { break; }
            str += "${timesPlayed} pessoas jogam ${game}!\n";
            idx++;
        }

        context.sendMessage(str);
    }
}