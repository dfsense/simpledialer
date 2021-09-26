package ua.kovalev;

import java.util.Arrays;

public class Network {
    private String [] basePhoneNumbers;
    private int curIndex;

    public Network() {
        this.basePhoneNumbers = new String[10];
        this.curIndex = -1;
    }

    public boolean addPhoneNumber(String phoneNumber) throws ErrorAddPhoneNumberException {

        checkFormatPhoneNumber(phoneNumber);

        if(isPhoneNumber(phoneNumber))
            throw new ErrorAddPhoneNumberException("Неудача во время регистрации номера телефона. Такой номер телефона в базе уже существует");

        if(this.curIndex == basePhoneNumbers.length-1){
            increaseSizeArray();
        }

        addPhone(phoneNumber);

        System.out.printf("Номер телефона [%s] успешно был зарегистрирован в мобильной сети\n", phoneNumber);
        return true;
    }

    public boolean isPhoneNumber(String phoneNumber){
        String findedPhoneNumber = Arrays.stream(basePhoneNumbers).filter(phone->phoneNumber.equals(phone)).findAny().orElse(null);
        return (findedPhoneNumber==null) ? false : true;
    }

    public int getCapacityBase(){
        return basePhoneNumbers.length;
    }

    private void checkFormatPhoneNumber(String phoneNumber) throws ErrorAddPhoneNumberException{
        if (phoneNumber.charAt(0) != '+' || (phoneNumber.length()!=13))
            throw new ErrorAddPhoneNumberException("Неудача во время регистрации номера телефона. Не верный формат номера");
    }

    private void increaseSizeArray(){
        basePhoneNumbers = Arrays.copyOf(basePhoneNumbers, basePhoneNumbers.length+(basePhoneNumbers.length/2));
    }

    private void addPhone(String phoneNumber){
        basePhoneNumbers[++curIndex] = phoneNumber;
    }


}
