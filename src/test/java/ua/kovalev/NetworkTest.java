package ua.kovalev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    Network network;

    @BeforeEach
    void setUp(){
        network = new Network();
    }
    @Test
    void throwErrorAddPhoneNumberExceptionIfInvalidFormatPhoneNumber(){
        // есть 13 символов но нет плюса в начале
        try{
            network.addPhone(new Phone(network, "3805012345679"));
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Не верный формат номера";
            assertEquals(expectedMessage, ex.getMessage());
        }

        // в номере меньше 13 символов
        try{
            network.addPhone(new Phone(network, "380501234567"));
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Не верный формат номера";
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    @Test
    void throwErrorAddPhoneNumberExceptionIfPhoneNumberAlreadyAtendedInNetwork(){
        try{
            network.addPhone(new Phone(network, "+380503234354"));
            network.addPhone(new Phone(network, "+380503234354"));
        }catch (ErrorAddPhoneNumberException ex){
            String expectedMessage = "Неудача во время регистрации номера телефона. Такой номер телефона в базе уже существует";
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    @Test
    void testAddPhoneNumberSuccessfully(){
        Phone phone = new Phone(network, "+380503234354");
        try{
            assertTrue(network.addPhone(phone));
        }catch (ErrorAddPhoneNumberException ex){
            System.out.println(ex.getMessage());
        }
        assertTrue(network.getPhone(phone.getPhoneNumber())!=null);
    }

    @Test
    void testIsPhoneNumber(){
        Phone phone = new Phone(network, "+380503234354");
        assertFalse(network.getPhone(phone.getPhoneNumber())!=null);
        try{
            network.addPhone(phone);
        }catch (ErrorAddPhoneNumberException ex){
            System.out.println(ex.getMessage());
        }
        assertTrue(network.getPhone(phone.getPhoneNumber())!=null);
    }

    @Test
    void testIncreaseBaseArray(){
        assertEquals(10, network.getCapacityBase());
        try {
            network.addPhone(new Phone(network, "+380503234354"));
            network.addPhone(new Phone(network, "+380503234355"));
            network.addPhone(new Phone(network, "+380503234356"));
            network.addPhone(new Phone(network, "+380503234357"));
            network.addPhone(new Phone(network, "+380503234358"));
            network.addPhone(new Phone(network, "+380503234359"));
            network.addPhone(new Phone(network, "+380503234350"));
            network.addPhone(new Phone(network, "+380503234351"));
            network.addPhone(new Phone(network, "+380503234352"));
            network.addPhone(new Phone(network, "+380503234353"));
            assertEquals(10, network.getCapacityBase());
            network.addPhone(new Phone(network, "+380503244561"));
            assertEquals(15, network.getCapacityBase());
        } catch (ErrorAddPhoneNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}