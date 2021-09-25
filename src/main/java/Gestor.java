import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Gestor {
    public static boolean PacienteLogExist(){
        Path archivo = Paths.get(Init.rutaP);
        return archivo.toFile().canWrite();
    }
    public static boolean EspecialistaLogExist(){
        Path archivo = Paths.get(Init.rutaE);
        return archivo.toFile().canWrite();
    }
    public static void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leído");
        }
        return contenido;
    }
    public static void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        crearArchivo(ruta, oldContenido + "\n" + contenido);
    }
    public static void eliminarArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        try {
            Files.deleteIfExists(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarEspecialistas(){
        eliminarArchivo(Init.rutaE);
        crearArchivo(Init.rutaE,"ID,Nombre,Especialista,Fonasa,Isapres");
        for (Especialista e: Especialista.especialistas){
            nuevaLinea(Init.rutaE,e.toString());
        }
    }
    public static void guardarPacientes(){
        eliminarArchivo(Init.rutaP);
        crearArchivo(Init.rutaP,"Nombre,Apellido,Rut,Especialista,RutValido,FechaAgendada,FechaRealizada");
        for (Paciente p: Paciente.pacientes) {
            nuevaLinea(Init.rutaP,p.toString());
        }
    }
}
