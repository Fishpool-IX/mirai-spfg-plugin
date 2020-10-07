package net.im45.bot.spfg

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.getGroupOrNull
import net.mamoe.mirai.contact.User
import net.mamoe.mirai.contact.nameCardOrNick
import java.lang.Appendable

object SpfgCmd : CompositeCommand(
    Spfg, "SPFG",
    description = "Salted Powerless Fish Group"
) {
    @SubCommand
    suspend fun CommandSender.iam(name: String) {
        SpfgConfig.self.run {
            add(name)
            sendMessage("Alright, now I am " + joinToString(", "))
        }
    }

    @SubCommand
    suspend fun CommandSender.iamnot(name: String) {
        SpfgConfig.self.run {
            if (remove(name)) {
                sendMessage("Alright, I am no longer $name")
            } else {
                sendMessage("But I am not $name!")
            }
        }
    }

    @SubCommand
    suspend fun CommandSender.theyis(target: User, alias: String) {
        SpfgConfig.alias[target.id] = alias
        sendMessage("Alright, they is $alias now")
    }

    @SubCommand
    suspend fun CommandSender.theyisnot(target: User) {
        SpfgConfig.alias.remove(target.id)?.let {
            sendMessage("Alright, they is no longer $it")
        } ?: sendMessage("But they has no name!")
    }

    @SubCommand
    suspend fun CommandSender.whoami() {
        SpfgConfig.self.run {
            if (isEmpty())
                sendMessage("Fuck, I have no name")
            else
                sendMessage("I am " + joinToString(", "))
        }
    }
}
