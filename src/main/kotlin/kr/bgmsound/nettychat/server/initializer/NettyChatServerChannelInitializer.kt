package kr.bgmsound.nettychat.server.initializer

import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import kr.bgmsound.nettychat.server.handler.NettyChatServerHandler


class NettyChatServerChannelInitializer : ChannelInitializer<SocketChannel>() {
    @Throws(Exception::class)
    override fun initChannel(socketChannel: SocketChannel) {
        val pipeline: ChannelPipeline = socketChannel.pipeline()
        pipeline.addLast(StringDecoder())
        pipeline.addLast(StringEncoder())
        pipeline.addLast(NettyChatServerHandler())
    }
}