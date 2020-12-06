package com.example.nugget;
public class StringUtils {

    public static final String python = "python3" ;
    public static final String snipperLocation = "/Users/apple/Desktop/nugget/python_script/snipper.py" ;
    public static final String ffmpegPath= "/Users/apple/Desktop/nugget/python_script/ffmpeg" ;
    public static final String episodeDownloadPath = "/Users/apple/Desktop/episode_download";
    public static final String clipsDownloadPath = "/Users/apple/Desktop/clips_download" ;


    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotNullOrEmpty(String value) {
        return !isNullOrEmpty(value);
    }
}
