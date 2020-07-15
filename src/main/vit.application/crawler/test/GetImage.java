package crawler.test;

import jpa.AnimeJPA;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class GetImage {
    public static void main(String[] args) throws IOException {
        AnimeJPA animeJPA = new AnimeJPA();
        List<String> imageLinks = animeJPA.getImageLink();
        int count = 179553;
        for (String imageLink : imageLinks) {
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(imageLink).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("/home/victor/Downloads/TrainImage/image" + (++count) + ".jpg");
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        }
    }
}
