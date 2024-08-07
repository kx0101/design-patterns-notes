# Intent

Adapter is a structural design pattern, which allows incompatible objects to collaborate. The adapter acts as a wrapper between two objects. It catches calls
for one object and transforms them to format and interface recognizable by the second object.

# Structure

![structure](/adapter/adapter.png)

# Components:

## 1. Target Interface

It's the interface that the clients expects. It defines the methods that the client will use.

```java
interface MediaPlayer {
    void play(String audioType, String fileName);
}
```

## 2. Adaptee

The existing class or component with an interface that is not compatible with the Target interface.

```java
class VLCPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}
```

## 3. Adapter

The class that implements the Target interface and internally delegates calls to the Adaptee.

```java
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
```

## 4. Client

The class or set of classes that use the Target interface.

```java
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
```

# Example

```java
public class Adapter {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("vlc", "video.vlc");
        audioPlayer.play("mp4", "movie.mp4");
    }
}
```

*Output:*
```
Playing MP3 file: song.mp3
Playing VLC file: video.vlc
Invalid media type. Only MP3 and VLC formats are supported.
```

# When to use it

- Use the Adapter pattern when you want to use some existing class, but its interface isn't compatible with the rest of your code.
- Use the Adapter pattern when you want to reuse several existing subclasses that lack some common functionality that can't be added to the superclass.

# Cons
- Adds extra layers, making the system more complex and harder to maintain.
- Misalignment between interfaces can make the adapter cumbersome.
- The Adaptee interface can only expose functionalities that the Adaptee provides.
