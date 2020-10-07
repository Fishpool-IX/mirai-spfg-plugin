package net.im45.bot.spfg

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.User

object IAmCmd : SimpleCommand(
    Spfg, "iam",
    description = "I am ..."
) {
    @Handler
    suspend fun CommandSender.iam(name: String) {
        SpfgConfig.self.run {
            add(name)
            sendMessage("Alright, now I am " + joinToString(", "))
        }
    }
}

object IAmNotCmd : SimpleCommand(
    Spfg, "iamnot",
    description = "I am not ..."
) {
    @Handler
    suspend fun CommandSender.iamnot(name: String) {
        SpfgConfig.self.run {
            if (remove(name)) {
                sendMessage("Alright, I am no longer $name")
            } else {
                sendMessage("But I am not $name!")
            }
        }
    }
}

object TheyIsCmd : SimpleCommand(
    Spfg, "theyis",
    description = "They is ..."
) {
    @Handler
    suspend fun CommandSender.theyis(target: User, alias: String) {
        SpfgConfig.alias[target.id] = alias
        sendMessage("Alright, they is $alias now")
    }
}

object TheyIsNotCmd : SimpleCommand(
    Spfg, "theyisnot",
    description = "They is not ..."
) {
    @Handler
    suspend fun CommandSender.theyisnot(target: User) {
        SpfgConfig.alias.remove(target.id)?.run {
            sendMessage("Alright, they is no longer $this")
        }?:run {
            sendMessage("But they has no name!")
        }
    }
}

object WhoAmICmd : SimpleCommand(
    Spfg, "whoami",
    description = "Who am I?"
) {
    @Handler
    suspend fun CommandSender.whoami() {
        SpfgConfig.self.run {
            if (isEmpty())
                sendMessage("Fuck, I have no name")
            else
                sendMessage("I am " + joinToString(", "))
        }
    }
}
