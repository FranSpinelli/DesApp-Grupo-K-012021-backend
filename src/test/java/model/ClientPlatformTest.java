package model;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ClientPlatform;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientPlatformTest {
    @Test
    void ClientPlatformConstructorGettersAndSettersTest() {
        ClientPlatform aClientPlatform = new ClientPlatform("Netflix", "123", "jose@gmail.com", "asd");

        assertNull(aClientPlatform.getId());
        assertEquals(aClientPlatform.getName(), "Netflix");
        assertEquals(aClientPlatform.getPassword(), "123");
        assertEquals(aClientPlatform.getContactMail(), "jose@gmail.com");
        assertEquals(aClientPlatform.getApiKey(), "asd");

        aClientPlatform.setId(1);
        aClientPlatform.setName("Amazon");
        aClientPlatform.setPassword("456");
        aClientPlatform.setContactMail("pepe@gmail.com");

        assertEquals(aClientPlatform.getId(), 1);
        assertEquals(aClientPlatform.getName(), "Amazon");
        assertEquals(aClientPlatform.getPassword(), "456");
        assertEquals(aClientPlatform.getContactMail(), "pepe@gmail.com");
    }

    @Test
    void ClientPlatformPasswordCheckingMethodTest(){
        ClientPlatform aClientPlatform = new ClientPlatform("Netflix", "123", "jose@gmail.com", "asd");

        assertTrue(aClientPlatform.canLogInWithGivenPass("123"));
    }
}