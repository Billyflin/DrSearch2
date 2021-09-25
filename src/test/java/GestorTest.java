import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {

    @Test
    void pacienteLogExist() {
        Path archivo = Paths.get(Init.rutaP);
        assertEquals("Pacientes.CSV", archivo.getFileName().toString());
    }

    @Test
    void especialistaLogExist() {
        Path archivo = Paths.get(Init.rutaE);
        assertEquals("Especialistas.CSV", archivo.getFileName().toString());
    }

    @Test
    void crearArchivo() {
        Path archivo = Paths.get(Init.rutaE);
        assertTrue(archivo.toFile().canWrite());
    }

    @Test
    void crearArchivo2() {
        Path archivo = Paths.get(Init.rutaP);
        assertTrue(archivo.toFile().canWrite());
    }
}