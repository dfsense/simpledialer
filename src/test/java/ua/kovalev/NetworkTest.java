package ua.kovalev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    Network network;

    public NetworkTest() {
        this.network = new Network();
    }

    @Test
    void throwErrorAddPhoneNumberExceptionIfInvalidFormatPhoneNumber(){
        // есть 13 символов но нет плюса в начале
        try{
            network.addPhoneNumber("3805012345679");
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Не верный формат номера";
            assertEquals(expectedMessage, ex.getMessage());
        }

        // в номере меньше 13 символов
        try{
            network.addPhoneNumber("380501234567");
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Не верный формат номера";
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    @Test
    void throwErrorAddPhoneNumberExceptionIfPhoneNumberAlreadyAtendedInNetwork(){
        String phoneNumber = "+380503234354";
        try{
            network.addPhoneNumber(phoneNumber);
            network.addPhoneNumber(phoneNumber);
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Такой номер телефона в базе уже существует";
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    @Test
    void testAddPhoneNumberSuccessfully(){
        String phoneNumber = "+380503234354";
        try{
            assertTrue(network.addPhoneNumber(phoneNumber));
        }catch (ErrorAddPhoneNumberException ex){
            System.out.println(ex.getMessage());
        }
        assertTrue(network.isPhoneNumber(phoneNumber));
    }

    @Test
    void testIsPhoneNumber(){
        String phoneNumber = "+380503234354";
        assertFalse(network.isPhoneNumber(phoneNumber));
        try{
            network.addPhoneNumber(phoneNumber);
        }catch (ErrorAddPhoneNumberException ex){
            System.out.println(ex.getMessage());
        }
        assertTrue(network.isPhoneNumber(phoneNumber));
    }

    @Test
    void testIncreaseBaseArray(){
        assertEquals(10, network.getCapacityBase());
        try {
            network.addPhoneNumber("+380503244567");
            network.addPhoneNumber("+380503244568");
            network.addPhoneNumber("+380503244569");
            network.addPhoneNumber("+380503244561");
            network.addPhoneNumber("+380503244562");
            network.addPhoneNumber("+380503244563");
            network.addPhoneNumber("+380503244564");
            network.addPhoneNumber("+380503244565");
            network.addPhoneNumber("+380503244566");
            network.addPhoneNumber("+380503244551");
            assertEquals(10, network.getCapacityBase());
            network.addPhoneNumber("+380503244552");
            assertEquals(15, network.getCapacityBase());
        } catch (ErrorAddPhoneNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}