package com.github.littleBitsman.Color3;

import org.jetbrains.annotations.Nullable;
import com.github.littleBitsman.Color3.opencv_scalar_repackaged.Scalar;

/**
 * Better Scalar class that allows conversion between the two (this and Scalar) and stores three color values.
 */
public final class Color3 {
    public final double R;
    public final double G;
    public final double B;
    public Color3(double R, double G, double B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }
    public Color3(double[] vals) {
        this.R = vals[0];
        this.G = vals[1];
        this.B = vals[2];
    }
    @Override
    public String toString() {
        return "Color3(" + this.R + ", " + this.G + ", " + this.B + ")";
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof Scalar) {
            double[] otherVal = ((Scalar) other).val;
            return this.R == otherVal[0] && this.G == otherVal[1] && this.B == otherVal[2];
        } else if (other instanceof Color3 otherC) {
            return this.R == otherC.R && this.G == otherC.G && this.B == otherC.B;
        } else return false;
    }

    /**
     * Converts this Color3 to a Scalar with the equivalent R, G and B values and an A value of 0.
     * @return Equivalent Scalar with the R, G and B values and an A value of 0.
     */
    public Scalar toScalar() {
        return new Scalar(this.R, this.G, this.B);
    }

    /**
     * Converts this Color3 to a Scalar with the equivalent R, G and B values and a set A value in the function. A defaults to 0.
     * @param A The A value of the Scalar.
     * @return Equivalent Scalar with the R, G and B values and the set A value.
     */
    public Scalar toScalar(double A) {
        return new Scalar(this.R, this.G, this.B, A);
    }

    /**
     * Equivalent to the initializer of Color3. (Basically only exists for people who don't like using regular initializers lol)
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return A new Color3.
     */
    public static Color3 fromRGB(double r, double g, double b) {
        return new Color3(r, g, b);
    }

    /**
     * Creates a new Color3 using HSV values instead of the default RGB values. Very unstable and inaccurate.
     * @param h Hue value
     * @param s Saturation value
     * @param v Vibrance value
     * @return A new Color3.
     */
    @Deprecated(since="1.0")
    public static Color3 fromHSV(double h, double s, double v) {
        return Color3.HSVtoRGB(h,s,v);
    }

    /**
     * Converts a Scalar to a Color3. The Scalar's A value is discarded.
     * @param scalar The Scalar to be converted.
     * @return Color3 with equivalent R, G and B values of scalar.
     */
    public static Color3 fromScalar(Scalar scalar) {
        double[] val = scalar.val;
        return new Color3(val[0], val[1], val[2]);
    }

    /**
     * Util function that converts HSV (also known as HSB) to RGB. Very unreliable and it is not recommended to use this.
     * @author <a href="https://stackoverflow.com/questions/7896280/">Stack Overflow question this came from.</a>
     * @param hue Hue value.
     * @param saturation Saturation value.
     * @param value Brightness value (also known as "value")
     * @return A Color3 with the corresponding RGB value of the passed in HSV/HSB value.
     */
    @Deprecated(since="1.0")
    public static Color3 HSVtoRGB(double hue, double saturation, double value) {
        return new Color3(HSVtoRGBIntArray(hue, saturation > 1 ? saturation/100 : saturation, value > 1 ? value/100 : value));
    }
    private static double[] HSVtoRGBIntArray(double hue, double saturation, double value) {
        int r = 0;
        int g = 0;
        int b = 0;
        if (saturation == 0.0F) {
            b = (int)(value * 255.0F + 0.5F);
            g = b;
            r = b;
        } else {
            double h = (hue - Math.floor(hue)) * 6.0F;
            double f = h - Math.floor(h);
            double p = value * (1.0F - saturation);
            double q = value * (1.0F - saturation * f);
            double t = value * (1.0F - saturation * (1.0F - f));
            switch ((int) h) {
                case 0 -> {
                    r = (int) (value * 255.0F + 0.5F);
                    g = (int) (t * 255.0F + 0.5F);
                    b = (int) (p * 255.0F + 0.5F);
                }
                case 1 -> {
                    r = (int) (q * 255.0F + 0.5F);
                    g = (int) (value * 255.0F + 0.5F);
                    b = (int) (p * 255.0F + 0.5F);
                }
                case 2 -> {
                    r = (int) (p * 255.0F + 0.5F);
                    g = (int) (value * 255.0F + 0.5F);
                    b = (int) (t * 255.0F + 0.5F);
                }
                case 3 -> {
                    r = (int) (p * 255.0F + 0.5F);
                    g = (int) (q * 255.0F + 0.5F);
                    b = (int) (value * 255.0F + 0.5F);
                }
                case 4 -> {
                    r = (int) (t * 255.0F + 0.5F);
                    g = (int) (p * 255.0F + 0.5F);
                    b = (int) (value * 255.0F + 0.5F);
                }
                case 5 -> {
                    r = (int) (value * 255.0F + 0.5F);
                    g = (int) (p * 255.0F + 0.5F);
                    b = (int) (q * 255.0F + 0.5F);
                }
            }
        }

        int ree = -16777216 | r << 16 | g << 8 | b;
        return new double[]{ree >> 16 & 255, ree >> 8 & 255, ree & 255};
    }
    private static String rgbToString(double r, double g, double b) {
        String rs = Integer.toHexString((int)(r * 256));
        String gs = Integer.toHexString((int)(g * 256));
        String bs = Integer.toHexString((int)(b * 256));
        return rs + gs + bs;
    }
}