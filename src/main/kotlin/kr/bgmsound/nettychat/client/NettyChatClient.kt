package kr.bgmsound.nettychat.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import kr.bgmsound.nettychat.client.initializer.NettyChatClientChannelInitializer
import java.io.BufferedReader
import java.io.InputStreamReader


class NettyChatClient(
    private val host: String,
    private val port: Int,
) {
    @Throws(Exception::class)
    fun run() {
        val group: EventLoopGroup = NioEventLoopGroup()

        try {
            val bootstrap: Bootstrap = Bootstrap()
                .group(group)
                .channel(NioSocketChannel::class.java)
                .handler(NettyChatClientChannelInitializer())

            val channel: Channel = bootstrap.connect(host, port).sync().channel()
            println("Successfully joined server")

            val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
            while (true) {
                val line = bufferedReader.readLine() ?: break
                channel.writeAndFlush(line)
            }
        } finally {
            group.shutdownGracefully()
        }
    }
}

fun main() {
    NettyChatClient("localhost", 8080).run()
}