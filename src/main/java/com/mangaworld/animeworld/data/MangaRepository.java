package com.mangaworld.animeworld.data;
import com.mangaworld.animeworld.models.Manga;
import org.springframework.data.repository.CrudRepository;

public interface MangaRepository extends CrudRepository<Manga, Long> {

}
