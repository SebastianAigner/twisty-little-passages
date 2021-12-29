import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun BufferedImage.savePng(filename: String) {
    ImageIO.write(this, "png", File(filename))
}