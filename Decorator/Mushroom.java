package Decorator;

public class Mushroom extends ToppingDecorator{
    BasePizza base;
    Mushroom(BasePizza base){
       this.base=base;
    }
    int cost()
    {
       return (base.cost()+10);
    }
}
