import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
    }

    @Test
    void crearArchivo() {
    }

    @Test
    void leerArchivo() {
    }

    @Test
    void listaArchivos() {
    }

        String rut="19763564-9";
    @Test
    void vRuts() {
        assertTrue(Main.validarRut(rut));
    }
}