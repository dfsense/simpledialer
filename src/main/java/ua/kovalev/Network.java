package ua.kovalev;

import java.util.Arrays;

public class Network {
    private Phone [] basePhones;
    private int curIndex;

    public Network() {
        this.basePhones = new Phone[10];
        this.curIndex = -1;
    }

    public boolean addPhone(Phone phone) throws ErrorAddPhoneNumberException {

        checkFormatPhoneNumber(phone.getPhoneNumber());

        if(getPhone(phone.getPhoneNumber())!=null)
            throw new ErrorAddPhoneNumberException("Неудача во время регистрации номера телефона. Такой номер телефона в базе уже существует");

        if(this.curIndex == basePhones.length-1){
            increaseSizeArray();
        }

        basePhones[++curIndex] = phone;

        System.out.printf("Номер телефона [%s] успешно был зарегистрирован в мобильной сети\n", phone.getPhoneNumber());
        return true;
    }

    public Phone getPhone(String phoneNumber){
        Phone findedPhone = null;
        for (int i = 0; i <= curIndex; i++) {
            if(basePhones[i]==null){
                break;
            }
            if (basePhones[i].getPhoneNumber().equals(phoneNumber)){
                findedPhone = basePhones[i];
                break;
            }
            if(i==curIndex){
                break;
            }
        }
//        Phone findedPhone = Arrays.stream(basePhones).filter(phone->phone.getPhoneNumber().equals(phoneNumber)).findAny().orElse(null);
        return findedPhone;
    }

    public int getCapacityBase(){
        return basePhones.length;
    }

    private void checkFormatPhoneNumber(String phoneNumber) throws ErrorAddPhoneNumberException{
        if (phoneNumber.charAt(0) != '+' || (phoneNumber.length()!=13))
            throw new ErrorAddPhoneNumberException("Неудача во время регистрации номера телефона. Не верный формат номера");
    }

    private void increaseSizeArray(){
        basePhones = Arrays.copyOf(basePhones, basePhones.length+(basePhones.length/2));
    }


    public boolean call(String phoneNumberSrc, String phoneNumberDest){
        System.out.printf("Абонент [%s] вызывает абонента [%s]\n", phoneNumberSrc, phoneNumberDest);
        if (getPhone(phoneNumberSrc)==null){
            System.out.printf("Неудача, номер исходящего телефона не зарегистрирован в мобильной сети [%s]\nОтмена вызова", phoneNumberSrc);
            return false;
        }
        Phone phoneDest = getPhone(phoneNumberDest);
        if (phoneDest == null){
            System.out.printf("Неудача, номер телефона вызываемого абонента не зарегистрирован в мобильной сети [%s]\nОтмена вызова", phoneNumberDest);
            return false;
        }
        phoneDest.callIn(phoneNumberSrc);
        return true;
    }


}
