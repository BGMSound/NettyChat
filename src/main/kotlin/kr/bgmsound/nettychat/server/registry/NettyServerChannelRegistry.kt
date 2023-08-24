package kr.bgmsound.nettychat.server.registry

import io.netty.channel.Channel

object NettyServerChannelRegistry {
    private val channels = mutableSetOf<Channel>()

    fun registerChannel(channel: Channel) {
        channels.add(channel)
    }

    fun removeChannel(channel: Channel) {
        channels.remove(channel)
    }

    fun contains(channel: Channel) = channels.contains(channel)

    fun getChannels() = channels.toSet()
}