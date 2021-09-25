import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EspecialistaTest {
    Especialista e;
    int ID=89;
    String nombre = "Liliana Hernandez";
    String especialista ="Médico General";
    boolean fonasa =true;
    boolean isapre =false;


    @BeforeEach
    void setUp() {
        e = new Especialista(ID, nombre, especialista,fonasa,isapre);
    }

    @AfterEach
    void tearDown() {
        e=null;
    }

    @Test
    void mostrarEspecialistasFonasa() {
        Especialista.especialistas.add(e);
        for (Especialista e: Especialista.especialistas
        ) {
            Assertions.assertTrue(e.isFonasa());
        }
    }

    @Test
    void mostrarEspecialistasIsapre() {
        Especialista.especialistas.add(e);
        for (Especialista e: Especialista.especialistas
        ) {
            Assertions.assertFalse(e.isIsapre());
        }
    }

    @Test
    void consultarHoras() {
    }

    @Test
    void getId() {
        assertEquals(89,e.getId());
    }

    @Test
    void getNombre() {
        assertEquals("Liliana Hernandez",e.getNombre());
    }

    @Test
    void getEspecialidad() {
        assertEquals("Médico General",e.getEspecialidad());
    }

    @Test
    void isFonasa() {
        Assertions.assertTrue(e.isFonasa());
    }

    @Test
    void isIsapre() {
        Assertions.assertFalse(e.isIsapre());
    }

    @Test
    void testToString() {
        assertEquals("89,Liliana Hernandez,Médico General,true,false",e.toString());
    }
}