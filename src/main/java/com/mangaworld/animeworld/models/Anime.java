package com.mangaworld.animeworld.models;

public class Anime {
    private String animeName;
    private String genre;
    private String description;
    private int episodes;

    public Anime() {
        this.animeName = animeName;
        this.genre = genre;
        this.description = description;
        this.episodes = episodes;
    }


    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "Your Anime";
    }

}
