class Thing {
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
}