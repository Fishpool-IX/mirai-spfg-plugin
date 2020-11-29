package net.im45.bot.spfg

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.UserCommandSender
import net.mamoe.mirai.contact.User
import net.mamoe.mirai.contact.nameCardOrNick

object SpfgCmd : CompositeCommand(
        Spfg, "SPFG",
        description = "Salted Powerless Fish Group"
) {
    @SubCommand
    suspend fun UserCommandSender.iam(alias: String) {
        SpfgConfig.alias[user.id] = alias
        sendMessage("好的，$alias")
    }

    @SubCommand
    suspend fun UserCommandSender.iamnot() {
        SpfgConfig.alias.remove(user.id)?.let { alias ->
            sendMessage("你不再是 $alias 了")
        } ?: sendMessage("可你本来就是 ${user.nameCardOrNick}")
    }

    @SubCommand
    suspend fun UserCommandSender.whoami() {
        sendMessage("你是 ${SpfgConfig.alias[user.id] ?: user.nameCardOrNick}")
    }

    @SubCommand
    suspend fun CommandSender.youare(vararg names: String) {
        SpfgConfig.self.run {
            names.forEach(::add)
            sendMessage("好，现在我是 ${joinToString("，")}")
        }
    }

    @SubCommand
    suspend fun CommandSender.youarenot(name: String) {
        sendMessage(
                if (SpfgConfig.self.remove(name))
                    "好，我不再是 $name 了"
                else
                    "可我本来就不是 $name"
        )
    }

    @SubCommand
    suspend fun CommandSender.whoareyou() {
        SpfgConfig.self.run {
            sendMessage(if (isEmpty()) "我什么都不是，我哭哭" else "我是 ${joinToString("，")}")
        }
    }

    @SubCommand
    suspend fun CommandSender.whois(target: User) {
        sendMessage("TA 是 ${SpfgConfig.alias[target.id] ?: target.nameCardOrNick}")
    }

    @SubCommand
    suspend fun CommandSender.theyis(target: User, alias: String) {
        SpfgConfig.alias[target.id] = alias
        sendMessage("TA 现在是 $alias 了")
    }

    @SubCommand
    suspend fun CommandSender.theyisnot(target: User) {
        SpfgConfig.alias.remove(target.id)?.let { alias ->
            sendMessage("TA 不再是 $alias 了")
        } ?: sendMessage("可 TA 什么都不是")
    }
}
