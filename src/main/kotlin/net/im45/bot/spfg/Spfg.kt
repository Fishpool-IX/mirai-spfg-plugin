package net.im45.bot.spfg

import com.google.auto.service.AutoService
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.GroupMessageEvent

@AutoService(JvmPlugin::class)
object Spfg : KotlinPlugin(
    JvmPluginDescription(
        "net.im45.bot.spfg",
        "0.1.0"
    )
) {
    override fun onEnable() {
        super.onEnable()

        subscribeAlways<GroupMessageEvent> {
            if (this.message.contentToString() in listOf("root!", "45gfg9!")) {
                reply(this.senderName + '!')
            }
        }
    }
}
