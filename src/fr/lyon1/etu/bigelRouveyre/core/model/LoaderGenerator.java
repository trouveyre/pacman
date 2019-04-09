package fr.lyon1.etu.bigelRouveyre.core.model;

import fr.lyon1.etu.bigelRouveyre.inter.model.Board;
import fr.lyon1.etu.bigelRouveyre.inter.model.Coordinates;
import fr.lyon1.etu.bigelRouveyre.inter.model.Generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoaderGenerator implements Generator {

    //CONSTRUCTORS
    public LoaderGenerator(String map) throws IOException {
        this.map = Files.readAllLines(Paths.get(map));
    }

    //FIELDS
    private List<String> map;

    //METHODS
    @Override
    public Board build() {
        Board result = new StandardBoard(new int[] {map.size(), map.get(0).length()});
        for (int i=0; i<map.size(); i++) {
            String row = map.get(i);
            for (int j=0; j<row.length(); j++) {
                char c = row.charAt(i);
                if (c == 'x')
                    result.addAt(StandardCoordinates.twoDimensions(i, j), StandardActor.wall());
            }
        }
        return result;
    }

    @Override
    public Set<Coordinates> freeCases() {
        return new HashSet<>();
    }

    @Override
    public Set<Coordinates> wallCases() {
        return new HashSet<>();
    }
}
