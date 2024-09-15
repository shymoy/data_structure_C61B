
import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;


public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int totalNotes = 37;

    private static double ithConcert(int i) {
        return CONCERT_A * Math.pow(2, (i - 24) / 12);
    }

    public static void main(String[] args) {
        //the string array to store what voic every string sing
        synthesizer.GuitarString[] strings = new synthesizer.GuitarString[totalNotes];

        //give them the song
        for (int i = 0; i < totalNotes; i++) {
            strings[i] = new GuitarString(ithConcert(i));
        }

        //sing it
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {

                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < keyboard.length(); i++) {
                    if (key == keyboard.charAt(i)) {
                        strings[i].pluck();
                        break;
                    }
                }
            }

            //compute the superpositino of samples
            double sample = 0.0;
            for (int i = 0; i < totalNotes; i++) {
                sample += strings[i].sample();
            }

            StdAudio.play(sample);

            //update the simulation of th string
            for (int i = 0; i < totalNotes; i++) {
                strings[i].tic();
            }
        }
    }
}