package com.mangaworld.animeworld.data;
import com.mangaworld.animeworld.models.AnimeGenre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<AnimeGenre, Long> {
}
