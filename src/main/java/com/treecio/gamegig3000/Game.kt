package com.treecio.gamegig3000

import com.treecio.gamegig3000.entity.Mob
import com.treecio.gamegig3000.entity.Player
import java.awt.Color
import java.awt.Graphics2D

class Game {

    private val startTime = System.currentTimeMillis()
    private val time get() = System.currentTimeMillis() - startTime

    val player = Player(App.WIDTH / 2, App.HEIGHT - 100)
    val mobs = ArrayList<Mob>()

    fun start() {
        mobs.add(Mob(App.WIDTH/2, 0, 0.0));
    }

    fun update(input: Input) {
        player.update(input)
        mobs.forEach { it.update(input) }
    }

    fun render(g: Graphics2D) {
        g.background = Color.white
        g.clearRect(0, 0, App.WIDTH, App.HEIGHT)

        g.color = Color.black
        g.drawString((time/1000.0).toString(), 20, 20)

        player.render(g)
        mobs.forEach { it.render(g) }
    }

}
