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
                executor.scheduleAtFixedRate({ app.run() },
                        0, (1000.0 / FPS).toLong(), TimeUnit.MILLISECONDS)
            }
        }
    }

    private val openBuffer = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)

    private val panel: JPanel
    private val startTime = System.currentTimeMillis()
    private val time get() = System.currentTimeMillis() - startTime

    init {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        panel = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.drawImage(openBuffer, 0, 0, null)
            }
        }
        panel.preferredSize = SCREEN_SIZE

        contentPane.add(panel)
        pack()
    }

    fun run() {
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

        g.color = Color.black
        g.drawString((time/1000.0).toString(), 20, 20)
    }

}


