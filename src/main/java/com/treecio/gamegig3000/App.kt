package com.treecio.gamegig3000

import com.treecio.gamegig3000.input.Keyboard
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

    private val game = Game()
    private val openBuffer = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
    private val keyboard = Keyboard()

    private val panel: JPanel

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

        addKeyListener(keyboard)
    }


    fun run() {
        game.update()
        game.render(openBuffer.graphics as Graphics2D)
        panel.revalidate()
        panel.repaint()
    }

}


