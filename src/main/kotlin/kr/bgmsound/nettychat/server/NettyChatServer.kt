package kr.bgmsound.nettychat.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import kr.bgmsound.nettychat.server.initializer.NettyChatServerChannelInitializer


class NettyChatServer(
    private val port: Int,
) {
    @Throws(Exception::class)
    fun run() {
        val bossGroup: EventLoopGroup = NioEventLoopGroup()
        val workerGroup: EventLoopGroup = NioEventLoopGroup()
        try {
            val bootstrap = ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(NettyChatServerChannelInitializer())
            val future = bootstrap.bind(port).sync()
            println("Server started on port $port")
            future.channel().closeFuture().sync()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }
}

fun main() {
    NettyChatServer(8080).run()
}