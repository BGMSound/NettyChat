package kr.bgmsound.nettychat.client.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter


class NettyChatClientHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(channelHandlerContext: ChannelHandlerContext, msg: Any) {
        val message = msg as String?
        println(message)
    }

    @Deprecated("Deprecated in Java")
    override fun exceptionCaught(channelHandlerContext: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        channelHandlerContext.close()
    }
}