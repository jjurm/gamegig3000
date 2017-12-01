package com.treecio.gamegig3000

import java.awt.image.BufferedImage


fun BufferedImage.deepCopy(): BufferedImage {
    val cm = colorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val raster = copyData(null)
    return BufferedImage(cm, raster, isAlphaPremultiplied, null)
}
