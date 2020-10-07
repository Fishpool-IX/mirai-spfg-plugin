package net.im45.bot.spfg

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object SpfgConfig : AutoSavePluginConfig("spfg") {
    val self: MutableSet<String> by value(mutableSetOf())
    val alias: MutableMap<Long, String> by value(mutableMapOf())
}
