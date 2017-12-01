package com.treecio.gamegig3000

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

class App : JFrame() {

    companion object {

        val WIDTH = 640
        val HEIGHT = 480
        val SCREEN_SIZE = Dimension(WIDTH, HEIGHT)
        val FPS = 30.0

        private val executor = Executors.newSingleThreadScheduledExecutor()

        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val app = App()
                app.isVisible = true
                app.pack()
                executor.scheduleAtFixedRate({ app.run() },
                        0, (1000.0 / FPS).toLong(), TimeUnit.MILLISECONDS)
            }
        }
    }

    private val frameBuffers = arrayOf(
            BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB),
            BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
    )
    private var openBufferIndex = 0
    private val openBuffer get() = frameBuffers[openBufferIndex]
    private val closedBuffer get() = frameBuffers[1 - openBufferIndex]
    private fun incrementBuffer() {
        openBufferIndex = 1 - openBufferIndex
    }

    private val panel: JPanel

    init {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        panel = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.drawImage(openBuffer, 0, 0, null)
            }
        }
        panel.size = SCREEN_SIZE

        contentPane.add(panel)
        pack()
    }

    fun run() {
        println(System.currentTimeMillis())
        update()
        render(openBuffer.graphics as Graphics2D)
        panel.revalidate()
        panel.repaint()
    }

    fun update() {

    }

    fun render(g: Graphics2D) {
        g.background = Color.white
        g.clearRect(0, 0, WIDTH, HEIGHT)
        g.color = Color.red
        g.fillRect(0, 0, WIDTH, HEIGHT)
    }

}


