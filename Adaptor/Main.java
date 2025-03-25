package Adaptor;

import Adaptor.Adaptee.WeightMachineForBabies;
import Adaptor.Adapter.WeightMachineAdapter;
import Adaptor.Adapter.WeightMachineAdapterImpl;

public class Main {
    public static void main(String[] args) {
        WeightMachineAdapter weightMachineAdapter=new WeightMachineAdapterImpl(new WeightMachineForBabies());

        System.out.println(weightMachineAdapter.getWeightInKg());
    }
}
