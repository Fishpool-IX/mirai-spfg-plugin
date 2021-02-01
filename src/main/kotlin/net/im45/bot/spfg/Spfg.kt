package net.im45.bot.spfg

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.permission.PermissionService
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent

object Spfg : KotlinPlugin(
    JvmPluginDescription(
        "net.im45.bot.spfg",
        "1.1.0",
        "SPFG"
    )
) {
    private val SPFG = Regex("(.+?)[!﹗！]+")

    internal val spfgAdmin by lazy {
        PermissionService.INSTANCE.register(permissionId("spfgAdmin"), "SPFG Admin")
    }

    override fun onEnable() {
        super.onEnable()

        SpfgConfig.reload()

        SpfgCmd.register()

        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            val match = SPFG.find(message.contentToString()) ?: return@subscribeAlways
            if (match.groupValues[1] in SpfgConfig.self)
                subject.sendMessage(SpfgConfig.alias.getOrDefault(sender.id, sender.nameCardOrNick) + '!')
        }
    }
}
