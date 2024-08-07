package adapter;

interface MediaPlayer {
    void play(String audioType, String fileName);
}


class VLCPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}


class MediaAdapter implements MediaPlayer {
    private VLCPlayer vlcplayer;

    public MediaAdapter() {
        this.vlcplayer = new VLCPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcplayer.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type. VLC player can only play VLC files.");
        }
    }
}


class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter();
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type. Only MP3 and VLC formats are supported.");
        }
    }
}


public class Adapter {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("vlc", "video.vlc");
        audioPlayer.play("mp4", "movie.mp4");
    }
}
