package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Explorer extends Hero {
    public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }

    public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException {
        if (this.isSpecialAction())
            throw new NotEnoughActionsException("You already used the special action");
        super.useSpecial();
        for (int i = 0; i < Game.map.length; i++) {
            for (int j = 0; j < Game.map.length; j++) {
                Game.map[i][j].setVisible(true);
            }
        }
        this.getSupplyInventory().get(0).use(this);
    }
}
