import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    String RutaEspecialistas = "Especialistas.txt";
    public static void main(String[] args) {
        Ingreso();
    }

    public static void Ingreso() {
        String RutPaciente = IngresarRut();
        MostrarPrevisiones();
        AgendarHora(IngresarPrevision(),RutPaciente);

    }

    public static void AgendarHora(String ruta, String rut) {
        if (ruta.equals("Fonasa.txt")){
            crearArchivo("Pacientes/"+rut+".txt",EscogerEspecialista(ruta)+";Fonasa");
        }else {
            crearArchivo("Pacientes/"+rut+".txt",EscogerEspecialista(ruta)+";Isapre");
        }
        System.out.println("Hora agendada con exito");
    }


    public static void MostrarPrevisiones() {
        System.out.println("Ingresa tu prevision");
        System.out.println("[1]Fonasa");
        System.out.println("[2]Isapre");
    }

    public static String IngresarPrevision() {
        int Prevision = Validar(2);
        if (Prevision==1){
            MostrarPrevision("Fonasa.txt");
            return "Fonasa.txt";
        }else {
            MostrarPrevision("Isapre.txt");
            return "Isapre.txt";
        }
    }

    public static void MostrarPrevision(String prevision) {
        System.out.println(leerArchivo(prevision));
    }

    public static String EscogerFecha() {
        Scanner teclado = new Scanner(System.in);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ingresar fecha en formato DD/MM/YYYY");
        Date fecha = null;
        String ingresoFecha=null;
        do {
            ingresoFecha = teclado.next();
            try {
                fecha = format.parse(ingresoFecha);
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto");
            }
        }while (fecha==null);
        return ingresoFecha;
    }

    public static String EscogerEspecialista(String ruta) {
        System.out.println("¿Con cual especialista desea agendar hora?");
        String[] Especialistas = leerArchivo(ruta).split("\n");
        return Especialistas[Validar(Especialistas.length)]+";"+EscogerFecha();
    }

    public static String IngresarRut() {
        Scanner teclado = new Scanner(System.in);
        String rut;
        do {
            System.out.println("Ingresar rut:");
            rut = teclado.next();

        }while (!validarRut(rut));
        return rut;
    }

    public static boolean validarRut(String rut) {   // 12.345.678-k
        boolean valido= false;
        rut = rut.toUpperCase();            // 12.345.678-K
        rut = rut.replace("-", "");         // 12.345.678K
        rut = rut.replace(".", "");         // 12345678k
        int dv = rut.charAt(rut.length()-1);
        try{
            int numRut = Integer.parseInt(rut.substring(0,rut.length()-1));

            int m=0, s=1;
            for (;numRut !=0;numRut/=10) {
                s = (s+numRut%10 *(9-m++%6))%11;
            }

            if(dv==(char)(s!=0 ?s+47:75)){
                valido = true;
            }

        }catch(Exception e){
            System.out.println("rut invalido");
        }

        return valido;
    }

    public static void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            System.out.println("El archivo fue creado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser creado");
        }
    }
    public void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        crearArchivo(ruta, oldContenido + "\n" + contenido);
    }

    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return contenido;
    }

    public static int Validar(int x) {
        int n = -1;
        do {
            //Scanner ponerlo dentro del DO, y dentro de una funcion
            Scanner teclado = new Scanner(System.in);
            //System.out.println("ingrese otro numero");
            try {
                n = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error");
            }
            if (n < 0 || n > x) {
                System.out.println("ingrese un numero valido");
            }
        } while (n < 0 || n > x);
        return n;
    }
}
