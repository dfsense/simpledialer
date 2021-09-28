package ua.kovalev;

public class Main {
    public static void main(String[] args) {
        Network network = new Network();

        Phone phone1 = new Phone(network, "+380501234567");
        Phone phone2 = new Phone(network, "+380502234568");
        phone1.registerNetwork();
        phone2.registerNetwork();

        phone1.callOut(phone2.getPhoneNumber());
    }
}
