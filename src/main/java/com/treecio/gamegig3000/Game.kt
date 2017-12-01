package com.treecio.gamegig3000

import com.treecio.gamegig3000.entity.Particle
import com.treecio.gamegig3000.entity.Projectile
import com.treecio.gamegig3000.entity.entities.Mob
import com.treecio.gamegig3000.entity.entities.Player
import com.treecio.gamegig3000.entity.spawn.MobSpawner
import com.treecio.gamegig3000.graphics.Sprite
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import java.awt.Color
import java.awt.Graphics2D
import java.util.*
import kotlin.collections.ArrayList

object Game {

    private var time = 0L
    private val starCount = 50;

    val player = Player()
    val mobs = ArrayList<Mob>()
    val bullets = ArrayList<Projectile>();
    val energyBar = EnergyBar()

    private val mobSpawner: MobSpawner = MobSpawner(App.WIDTH, App.HEIGHT)

    val stars = ArrayList<Particle>()

    fun start() {
        mobs.add(mobSpawner.spawn())
    }

    fun update(input: Input) {
        // update each entity
        stars.forEach { it.update(input) }
        player.update(input)
        bullets.forEach { it.update(input) }
        mobs.forEach { it.update(input) }

        // detect collisions
        for (mob in mobs) {
            if (player.collidesWith(mob)) {
                mob.kill()
                energyBar.consume(mob.damage)
            }
            if (mob.isAlive) {
                for (bullet in bullets) {
                    if (mob.collidesWith(bullet)) {
                        mob.kill()
                        bullet.kill()
                        break
                    }
                }
            }
        }

        // remove dead entities
        mobs.removeIf({ !it.isAlive })

        val iterate = mobs.listIterator()
        while (iterate.hasNext()) {
            val m = iterate.next()
            if (m.isRemoved) iterate.remove()
            else m.update(input)
            if (mobSpawner.canSpawn(this.time)) iterate.add(mobSpawner.spawn())
        }

        energyBar.update(input)

        time++
    }


    fun render(g: Graphics2D) {
        g.background = Color.black;
        g.clearRect(0, 0, App.WIDTH, App.HEIGHT)

        g.color = Color.white
        g.drawString(time.toString(), 20, 20)

        stars.forEach { it.render(g) }
        player.render(g)
        bullets.forEach { it.render(g) }
        mobs.forEach {
            it.render(g)
        }
        energyBar.render(g)
    }

    fun initializeBackground() {
        val r = Random()
        val starSpeed = 6;

        for (i in 0..starCount) {
            val speed = r.nextDouble()
            stars.add(Particle(
                    Vector2D(r.nextDouble() * App.WIDTH, r.nextDouble() * App.HEIGHT),
                    Vector2D(0.0, starSpeed + starSpeed * speed)
                    , 1 / (4 - 2 * speed)))
        }
    }

    fun addBullet(pos: Vector2D, angle: Double, sprites: List<Sprite>, scale: Double, speed: Double) {
        bullets.add(Projectile(pos, angle, sprites, scale, speed));
    }

}
