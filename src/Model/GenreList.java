package Model;

import DataHolder.*;
import java.util.Collections;
import java.util.LinkedList;

public class GenreList implements Genres{

    private LinkedList<Genre> genres;

    //Перенести в Model
    public OutputDataHolder validate(InputDataHolder holder){
        int id = Integer.parseInt(holder.getArguments()[0]);
        String[] arguments = holder.getArguments();
        OutputDataHolder outputDataHolder = new OutputDataHolder(id, arguments);
        if(id >= genres.size() || id < 0 || !(Integer.toString(id).equals(arguments[0])));//ошибка индексации
            outputDataHolder.setIndexError(true);
        if(holder.hasArguments()){
            Key[] keys = holder.getKeys();
            switch (keys[0]){
                case ADD:
                    if (getGenre(holder.getArguments()[0]) != null) {
                        outputDataHolder.setGenreEqualsNameError(true);
                    }
                    break;
                case EDIT:
                    if(getGenre(holder.getArguments()[1]) != null){
                        outputDataHolder.setGenreEqualsNameError(true);
                    }
                    break;
            }
        }
        return outputDataHolder;
    }

    @Override
    public void addGenre(String name) throws IllegalArgumentException{
        for (Genre genre : genres) {
            if (genre.getName().equals(name)) throw new IllegalArgumentException("Жанр с таким именем уже существует");
        }
        Genre newGenre = new Genre(name);
        int index = Collections.binarySearch(genres, newGenre);
        genres.add(index, newGenre);
    }

    @Override
    public void addGenre(Genre genre){
        genres.addLast(genre);
    }

    @Override
    public void removeGenre(int index){
        genres.remove(index);
    }

    @Override
    public void editName(int index, String newName)throws IllegalArgumentException{
        for (Genre genre : genres) {
            if (genre.getName().equals(newName)) throw new IllegalArgumentException("Жанр с таким именем уже существует");
        }
        Genre newGenre = genres.get(index);
        removeGenre(index);
        newGenre.setName(newName);
        int newIndex = Collections.binarySearch(genres, newGenre);
        genres.add(newIndex, newGenre);
        //сортирвка
    }

    public Genre getGenre(String name){
        for (Genre genre : genres) {
            if (genre.getName().equals(name))return genre;
        }
        return null;
    }

    @Override
    public Genre getGenre(int id) {
        return genres.get(id);
    }

    @Override
    public Genre[] getAllGenres() {
        return genres.toArray(new Genre[0]);
    }
}
