package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell {
    private Collectible collectible;

    public CollectibleCell(Collectible collectible,boolean isVisible) {
        super(isVisible);
        this.collectible = collectible;
    }

    public Collectible getCollectible() {
        return collectible;
    }
}
