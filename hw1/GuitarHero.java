
import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;


public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int TOTAL_NOTES = 37;

    private static double ithConcert(int i) {
        return CONCERT_A * Math.pow(2, (i - 24) / 12);
    }

    public static void main(String[] args) {
        //the string array to store what voic every string sing
        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[TOTAL_NOTES];

        //give them the song
        for (int i = 0; i < TOTAL_NOTES; i++) {
            strings[i] = new GuitarString(ithConcert(i));
        }

        //sing it
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {

                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < KEYBOARD.length(); i++) {
                    if (key == KEYBOARD.charAt(i)) {
                        strings[i].pluck();
                        break;
                    }
                }
            }

            //compute the superpositino of samples
            double sample = 0.0;
            for (int i = 0; i < TOTAL_NOTES; i++) {
                sample += strings[i].sample();
            }

            StdAudio.play(sample);

            //update the simulation of th string
            for (int i = 0; i < TOTAL_NOTES; i++) {
                strings[i].tic();
            }
        }
    }
}
