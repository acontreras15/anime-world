package com.mangaworld.animeworld.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Anime name cannot be blank")
    @Column(unique = true)
    private String mangaName;

    @NotNull(message = "Volumes are required")
    @Min(value = 1, message = "There is no manga with 0 volumes")
    private int volumes;

    @NotNull(message = "Episodes are required")
    @Min(value = 1, message = "There is no manga with 0 chapters")
    private int chapters;

    private String genre;

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL)
    private Set<Anime> animes;

    public Manga(){
        this.mangaName = "";
        this.genre = "";
        this.volumes = 0;
        this.chapters = 0;
    }

    public Manga(String mangaName, String animeGenre, int volumes, int episodes) {
        this.mangaName = mangaName;
        this.genre = animeGenre;
        this.volumes = volumes;
        this.chapters = episodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMangaName() {
        return mangaName;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public int getVolumes() {
        return volumes;
    }

    public void setVolumes(int volumes) {
        this.volumes = volumes;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(Set<Anime> animes) {
        this.animes = animes;
    }

    @Override
    public String toString(){return this.mangaName;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Manga))
            return false;

        Manga m = (Manga) o;

        return this.mangaName.equals(m.mangaName) && this.genre.equals(m.genre);
    }

    @Override
    public int hashCode() {
    return this.mangaName.hashCode();
    }
}
