package net.im45.bot.spfg

import com.google.auto.service.AutoService
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.GroupMessageEvent

@AutoService(JvmPlugin::class)
object Spfg : KotlinPlugin(
    JvmPluginDescription(
        "net.im45.bot.spfg",
        "0.1.0"
    )
) {
    private val SPFG = Regex("(.+?)[!﹗！]+")
    override fun onEnable() {
        super.onEnable()

        SpfgConfig.reload()

        SpfgCmd.register()

        subscribeAlways<GroupMessageEvent> {
            val callee = SPFG.find(message.contentToString())?.groupValues?.get(1) ?: return@subscribeAlways
            if (callee in SpfgConfig.self) sender.run {
                reply(SpfgConfig.alias.getOrDefault(id, nameCardOrNick) + '!')
            }
        }
    }
}
