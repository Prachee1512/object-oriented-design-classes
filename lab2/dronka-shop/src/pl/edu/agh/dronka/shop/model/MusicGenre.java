package pl.edu.agh.dronka.shop.model;

public enum MusicGenre {
    POP("Pop"),
    METAL("Metal"),
    HIPHOP("Hiphop");

    private String name;

    public String getDisplayName(){
        return this.name;
    }

    MusicGenre(String name) {
        this.name = name;
    }
}
