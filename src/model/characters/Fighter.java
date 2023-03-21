package model.characters;

public class Fighter extends Hero {
    public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }

    public static void main(String[] args) {
        Fighter f = new Fighter("X", 233, 2, 11);
        f.setCurrentHp(2);
        System.out.println(f.getCurrentHp());
    }
}
