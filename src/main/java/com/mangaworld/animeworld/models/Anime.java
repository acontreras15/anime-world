package com.mangaworld.animeworld.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank(message = "Anime name cannot be blank")
    @Column(unique = true)
    private String animeName;

    @NotBlank(message = "Anime needs a genre")
    private String genre;

    @NotBlank(message = "Anime needs a description")
    @Size(min = 1, message = "Description must be more than one character")
    private String description;

    @NotNull(message = "Episodes are required")
    @Min(value = 1, message = "There is no anime with 0 episodes")
    private int episodes;

    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany
    private Set<Weekly> weeklyAnime;

    public Anime(){
        this.animeName = "";
        this.genre = "";
        this.description = "";
        this.episodes = 0;
    }
    public Anime(String animeName, String genre, String description, int episodes) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public long getId() { return this.id; }

    public Set<Weekly> getWeeklyAnime() {
        return weeklyAnime;
    }

    public void setWeeklyAnime(Set<Weekly> weeklyAnime) {
        this.weeklyAnime = weeklyAnime;
    }

    @PrePersist
    public void onCreate(){
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(){
        this.setModified(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Your Anime";
    }

}
