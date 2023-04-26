import org.opencv.core.Scalar;

public class Examples {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        /*
         * Make a Color3 with a specific set of RGB values.
         */
        Color3 InitializedWithNew = new Color3(255, 255, 255);
        Color3 InitializedWithFromRGB = Color3.fromRGB(255, 255, 255);
        boolean Equivalent = InitializedWithNew == InitializedWithFromRGB; // Color3.new and Color3.fromRGB return the same thing.
        /*
         * Initialize a Color3 using a Scalar.
         */
        Scalar scalar = new Scalar(0, 255, 0);
        Color3.fromScalar(scalar); // Will have R=0, G=255, B=0.
        /*
         * Convert a Color3 to a Scalar.
         */
        Color3 ToScalar = new Color3(255, 255, 255);
        ToScalar.toScalar(0); // The A value can be omitted.
    }
}
