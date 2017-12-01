package com.treecio.gamegig3000

import com.treecio.gamegig3000.input.Keyboard
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

class App : JFrame() {

    companion object {

        val WIDTH = 1280
        val HEIGHT = 720
        val SCREEN_SIZE = Dimension(WIDTH, HEIGHT)
        val FPS = 24.0

        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val app = App()
                app.start()
            }
        }
    }

    private val game = Game()

    private val frameBuffers = (0..10).map { BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB) }
    private var openBufferIndex = 0
    private val openBuffer get() = frameBuffers[openBufferIndex]
    private val closedBuffer get() = frameBuffers[(openBufferIndex - 1 + frameBuffers.size) % frameBuffers.size]
    private fun incrementBuffer() {
        openBufferIndex = (openBufferIndex + 1) % frameBuffers.size
    }

    private val keyboard = Keyboard()

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var futureTask: ScheduledFuture<*>? = null
    private lateinit var panel: JPanel

    init {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        preferredSize = Dimension(WIDTH + 100, HEIGHT + 100)
        extendedState = extendedState or JFrame.MAXIMIZED_BOTH

        panel = object : JPanel() {
            override fun paintComponent(gg: Graphics) {
                val g = gg as Graphics2D
                g.background = Color.black
                g.clearRect(0, 0, panel.width, panel.height)

                val scale = Math.min(
                        panel.width.toDouble() / App.WIDTH,
                        panel.height.toDouble() / App.HEIGHT
                )
                val w = (scale * App.WIDTH).toInt()
                val h = (scale * App.HEIGHT).toInt()

                g.drawImage(closedBuffer, (panel.width - w) / 2, 0, w, h, null)
            }
        }
        //panel.preferredSize = SCREEN_SIZE

        contentPane.add(panel)
        pack()
        isVisible = true
        addKeyListener(keyboard)

    }

    fun start() {
        game.initializeBackground()
        game.start()
        futureTask = executor.scheduleAtFixedRate(this::run,
                0, (1000.0 / FPS).toLong(), TimeUnit.MILLISECONDS)
    }

    private fun run() {
        try {
            game.update(keyboard.newInput)
            game.render(openBuffer.graphics as Graphics2D)
            panel.revalidate()
            incrementBuffer()
            panel.repaint()
        } catch (e: Exception) {
            e.printStackTrace()
            futureTask?.cancel(false)
        }
    }

}


