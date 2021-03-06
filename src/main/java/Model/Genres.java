package Model;

import Controller.InputDataHolder;

public interface Genres {

    OutputDataHolder validateAddGenre(InputDataHolder command);

    void addGenre(String name);

    OutputDataHolder validateRemoveGenre(InputDataHolder command);

    void removeGenre(String index);

    void removeAllGenres();

    OutputDataHolder validateEditGenre(InputDataHolder command);

    void editName(String genre, String newName);

    Genre getGenre(String genre);

    Genre findGenre(String name);

    Genre[] getAllGenres();

    void addReadGenres(Genre[] genres, boolean duplicate);

}
