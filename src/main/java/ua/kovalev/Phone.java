package ua.kovalev;

public class Phone {
    private String phoneNumber;
    private boolean registeredNetwork;
    private Network network;

    public Phone(Network network, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.network = network;
        this.registeredNetwork = false;
    }

    public boolean registerNetwork() {
        try {
            network.addPhoneNumber(phoneNumber);
        } catch (ErrorAddPhoneNumberException e) {
            System.out.println(e.getMessage());
            return false;
        }
        registeredNetwork = true;
        return true;
    }

    public boolean callOut(Phone phone){
        System.out.printf("Пытаюсь вызвать абонента [%s]...\n", phone.getPhoneNumber());
        if (!registeredNetwork){
            System.out.printf("Неудача, мой номер телефона не зарегистрирован в мобильной сети [%s]\nОтмена вызова", phoneNumber);
            return false;
        }
        if (!network.isPhoneNumber(phone.getPhoneNumber())){
            System.out.printf("Неудача, номер телефона вызываемого абонента не зарегистрирован в мобильной сети [%s]\nОтмена вызова", phone.getPhoneNumber());
            return false;
        }
        phone.callIn(phoneNumber);
        return true;
    }

    public void callIn(String phoneNumber){
        System.out.printf("Вам звонит номер [%s]", phoneNumber);
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
