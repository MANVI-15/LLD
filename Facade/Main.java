package Facade;

public class Main {
    public static void main(String[] args) {
        ComputerSystemFacade computer = new ComputerSystemFacade();

        // User initiates the computer startup process with a single call
        computer.startComputer();
    }
}