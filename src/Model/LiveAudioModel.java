package Model;

import java.net.URL;

/**
 * Created by Simon on 2018-12-26.
 */
public class LiveAudioModel {
    private int id;
    private URL url;
    private String statkey;

    public LiveAudioModel(int id, URL url, String statkey){
        this.id = id;
        this.url = url;
        this.statkey = statkey;
    }
}
