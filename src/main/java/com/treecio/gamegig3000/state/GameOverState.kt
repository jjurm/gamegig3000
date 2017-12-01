package com.treecio.gamegig3000.state

import com.treecio.gamegig3000.App
import com.treecio.gamegig3000.Game
import com.treecio.gamegig3000.Input
import java.awt.Color
import java.awt.Font
import java.awt.Font.PLAIN
import java.awt.Graphics2D


object GameOverState : ScreenOverlayState() {

    override fun init() {}

    override fun getNextState(input: Input) = if (input.r) PlayGameState else GameOverState

    override fun update(input: Input) {
        // do nothing
    }

    override fun render(g: Graphics2D) {
        super.render(g)

        /*var count = 0;
        for (sprite in Sprite.gameOverSprite) {
            g.drawImage(sprite.image, 0+32*count+App.Companion.WIDTH/2-32*4, App.Companion.HEIGHT/2, 32, 32, null);
            count++;
        }*/


        val textSize = 32;
        /*val b: BufferedImage= BufferedImage(textSize*9, textSize, BufferedImage.TYPE_INT_ARGB_PRE);
        b.graphics.font = Font("OCR A Extended", PLAIN, textSize)
        b.graphics.color = Color.BLACK;
        b.graphics.drawString("GAME OVER", 0, 0);
        b.graphics.color = Color.RED
        b.graphics.fillRect(0, 0, b.width, b.height)
        g.drawImage(b, (App.Companion.WIDTH/2)-b.width/2, App.Companion.HEIGHT/2, 96, 32,  null);*/

        g.color = Color.BLACK;
        g.font = Font("OCR A Extended", PLAIN, textSize)
        g.drawString("GAME OVER", (App.Companion.WIDTH/2)-textSize*8/2, App.Companion.HEIGHT/2)
        g.drawString("Score: " + Game.time, (App.Companion.WIDTH/2)-textSize*9/2, App.Companion.HEIGHT/2 + 50)
        g.drawString("R for restart", (App.Companion.WIDTH/2)-textSize*10/2, App.Companion.HEIGHT/2 + 100)

        // show "Game over"
    }
}
