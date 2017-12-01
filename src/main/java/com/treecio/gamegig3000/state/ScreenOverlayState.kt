package com.treecio.gamegig3000.state

import com.treecio.gamegig3000.App
import java.awt.Color
import java.awt.Graphics2D

abstract class ScreenOverlayState : GameState {

    override fun render(g: Graphics2D) {
        g.drawImage(PlayGameState.lastFrame, 0, 0, null)

        g.color = Color(255, 255, 255, 128)
        val frameMargin = 80
        g.fillRect(frameMargin, frameMargin, App.WIDTH - 2 * frameMargin, App.HEIGHT - 2 * frameMargin)


    }

}
