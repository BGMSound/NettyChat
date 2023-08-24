package kr.bgmsound.nettychat.server.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import kr.bgmsound.nettychat.server.registry.NettyServerChannelRegistry
import java.time.LocalDateTime

class NettyChatServerHandler : ChannelInboundHandlerAdapter() {
    override fun channelActive(channelHandlerContext: ChannelHandlerContext) {
        val channel = channelHandlerContext.channel()
        if (!NettyServerChannelRegistry.contains(channel)) {
            NettyServerChannelRegistry.registerChannel(channel)
            println("[${LocalDateTime.now()}] ${channel.id()} joined the server")
        }
    }

    override fun channelInactive(channelHandlerContext: ChannelHandlerContext) {
        val channel = channelHandlerContext.channel()
        if (NettyServerChannelRegistry.contains(channel)) {
            NettyServerChannelRegistry.removeChannel(channel)
            println("[${LocalDateTime.now()}] ${channel.id()} quited the server")
        }
    }

    override fun channelRead(channelHandlerContext: ChannelHandlerContext, msg: Any) {
        val channel = channelHandlerContext.channel()
        val message = msg as String
        println("[${LocalDateTime.now()}] <${channel.id()}>: $message")

        NettyServerChannelRegistry.getChannels().forEach {
            if (channel != it) it.writeAndFlush("<${channel.id()}>: $message")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun exceptionCaught(channelHandlerContext: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        channelHandlerContext.close()
    }
}
