package Decorator;

public class Corn extends ToppingDecorator{
    BasePizza base;

    Corn(BasePizza base)
    {
        this.base=base;
    }
    @Override
    int cost() {
        return (base.cost()+20);
    }
}
