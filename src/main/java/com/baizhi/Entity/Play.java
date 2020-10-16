package com.baizhi.Entity;

public class Play {
    private String id;
    private Integer play_num;
    private String video_id;

    public Play() {
    }

    @Override
    public String toString() {
        return "Play{" +
                "id='" + id + '\'' +
                ", play_num=" + play_num +
                ", video_id='" + video_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlay_num() {
        return play_num;
    }

    public void setPlay_num(Integer play_num) {
        this.play_num = play_num;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public Play(String id, Integer play_num, String video_id) {
        this.id = id;
        this.play_num = play_num;
        this.video_id = video_id;
    }
}
