package org.firstinspires.ftc.teamcode

import org.firstinspires.ftc.teamcode.Pipeline.Color
import org.opencv.core.Scalar

internal fun HSVtoRGB(hue: Float, saturation: Float, v: Float): IntArray {
    var r = 0
    var g = 0
    var b = 0
    if (saturation == 0f) {
        b = (v * 255.0f + 0.5f).toInt()
        g = b
        r = g
    } else {
        val h = (hue - Math.floor(hue.toDouble()).toFloat()) * 6.0f
        val f = h - Math.floor(h.toDouble()).toFloat()
        val p = v * (1.0f - saturation)
        val q = v * (1.0f - saturation * f)
        val t = v * (1.0f - saturation * (1.0f - f))
        when (h.toInt()) {
            0 -> {
                r = (v * 255.0f + 0.5f).toInt()
                g = (t * 255.0f + 0.5f).toInt()
                b = (p * 255.0f + 0.5f).toInt()
            }
            1 -> {
                r = (q * 255.0f + 0.5f).toInt()
                g = (v * 255.0f + 0.5f).toInt()
                b = (p * 255.0f + 0.5f).toInt()
            }
            2 -> {
                r = (p * 255.0f + 0.5f).toInt()
                g = (v * 255.0f + 0.5f).toInt()
                b = (t * 255.0f + 0.5f).toInt()
            }
            3 -> {
                r = (p * 255.0f + 0.5f).toInt()
                g = (q * 255.0f + 0.5f).toInt()
                b = (v * 255.0f + 0.5f).toInt()
            }
            4 -> {
                r = (t * 255.0f + 0.5f).toInt()
                g = (p * 255.0f + 0.5f).toInt()
                b = (v * 255.0f + 0.5f).toInt()
            }
            5 -> {
                r = (v * 255.0f + 0.5f).toInt()
                g = (p * 255.0f + 0.5f).toInt()
                b = (q * 255.0f + 0.5f).toInt()
            }
        }
    }
    val ree = -0x1000000 or (r shl 16) or (g shl 8) or (b shl 0)
    return intArrayOf(ree shr 16 and 0xFF, ree shr 8 and 0xFF, ree shr 0 and 0xFF)
}

class Color3(var R: Double = 0.0, var G: Double = 0.0, var B: Double = 0.0) {
    companion object {
        fun new(r: Double, g: Double, b: Double): Color3 {
            assert(r in 0.0..255.0) { "Values cannot be out of range 0 to 255 (inclusive)." }
            assert(g in 0.0..255.0) { "Values cannot be out of range 0 to 255 (inclusive)." }
            assert(b in 0.0..255.0) { "Values cannot be out of range 0 to 255 (inclusive)." }
            return Color3(r * 255, g * 255, b * 255)
        }
        fun fromRGB(r: Double, g: Double, b: Double): Color3 = Color3(r, g, b)
        fun fromHSV(h: Double, s: Double, v: Double): Color3 {
            val color = HSVtoRGB(h.toFloat(), s.toFloat(), v.toFloat())
            return Color3(color[0].toDouble(), color[1].toDouble(), color[2].toDouble())
        }
        fun fromScalar(scalar: Scalar): Color3 {
            val vals: DoubleArray = scalar.`val`
            return Color3(vals[0], vals[1], vals[2])
        }
    }

    override fun toString(): String {
        return "Color3($R, $G, $B)"
    }

    override operator fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is Scalar) {
            val otherVals = other.`val`
            return (this.R == otherVals[0]) && (this.G == otherVals[1]) && (this.B == otherVals[2])
        } else if (other is Color3) {
            return this.R == other.R && this.G == other.G && this.B == other.B
        }
        return false
    }

    fun toScalar(A: Double?): Scalar {
        return Scalar(this.R, this.G, this.B, ((A ?: 0) as Double))
    }
}

fun Scalar.toColor3(): Color3 = Color3.fromScalar(this)
