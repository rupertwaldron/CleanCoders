package com.ruppyrup.episode33.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

class VideoFile {
    private String filename;

    public VideoFile(String filename) {
        this.filename = filename;
    }
}

interface Codec {

}

class OggCompressionCodec implements Codec {
}

class MPEG4CompressionCodec implements Codec {
}

class CodecFactory {
    public Codec extract(VideoFile file) {
        return new OggCompressionCodec();
    }
}

class BitrateReader {
    public static BufferedReader read(String filename, Codec sourceCodec) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filename));
    }

    public static String convert(BufferedReader buffer, Codec destinationCodec) {
        return "filename";
    }
}

class AudioMixer {
    public String fix(String result) {
        return "fixedFile";
    }
}

/**
 * This is the Facade which just has one public method and cleans up the interaction
 * with all the other classes
 */
public class VideoConverter {

    public File convert(String filename, String format) throws FileNotFoundException {

        VideoFile file = new VideoFile(filename);
        Codec sourceCodec = new CodecFactory().extract(file);
        Codec destinationCodec;
        if (Objects.equals(format, "mp4"))
            destinationCodec = new MPEG4CompressionCodec();
        else
            destinationCodec = new OggCompressionCodec();
        BufferedReader buffer = BitrateReader.read(filename, sourceCodec);
        String result = BitrateReader.convert(buffer, destinationCodec);
        result = (new AudioMixer()).fix(result);
        return new File(result);
    }

    public static void main(String[] args) throws FileNotFoundException {
        VideoConverter videoConverter = new VideoConverter();
        File mp4 = videoConverter.convert(".gitignore", "mp4");
        System.out.println(mp4.toString());
    }
}
