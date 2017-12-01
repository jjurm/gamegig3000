package com.treecio.gamegig3000

import com.treecio.gamegig3000.entity.Mob
import com.treecio.gamegig3000.entity.Particle
import com.treecio.gamegig3000.entity.Player
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import java.awt.Color
import java.awt.Graphics2D
import java.util.*

class Game {

    private val startTime = System.currentTimeMillis()
    private val time get() = System.currentTimeMillis() - startTime
    private val starCount = 20;

    val player = Player()
    val mobs = ArrayList<Mob>()

    val stars = ArrayList<Particle>()

    fun start() {
        mobs.add(Mob(Vector2D(App.WIDTH / 2.0, 0.0), 0.0))
    }

    fun update(input: Input) {
        stars.forEach{ it.update(input)}
        player.update(input)
        mobs.forEach { it.update(input) }
    }

    fun render(g: Graphics2D) {
        g.background = Color.black
        g.clearRect(0, 0, App.WIDTH, App.HEIGHT)

        g.color = Color.white
        g.drawString((time / 1000.0).toString(), 20, 20)

        stars.forEach{ it.render(g) }
        player.render(g)
        mobs.forEach { it.render(g) }
    }

    fun initializeBackground() {
        val r = Random()
        val starSpeed = 2;

        for (i in 0..starCount) {
            stars.add(Particle(
                    Vector2D(r.nextDouble() * App.WIDTH, r.nextDouble() * App.HEIGHT),
                    Vector2D(0.0, starSpeed + starSpeed * r.nextDouble())
            ))
        }
    }

}
