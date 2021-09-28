package ua.kovalev;

public class Phone {
    private String phoneNumber;
    private Network network;

    public Phone(Network network, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.network = network;
    }

    public boolean registerNetwork() {
        try {
            network.addPhone(this);
        } catch (ErrorAddPhoneNumberException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void callOut(String phoneNumber){
        network.call(this.phoneNumber, phoneNumber);
    }

    public void callIn(String phoneNumber){
        System.out.printf("абонента [%s] вызывает абонент [%s]\n", this.getPhoneNumber(), phoneNumber);
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }


}
