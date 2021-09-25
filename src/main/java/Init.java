import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Init {
    static final String rutaE="src/main/resources/Especialistas.CSV";
    static final String rutaP="src/main/resources/Pacientes.CSV";

    public static void main(String[] args) {
        ingreso();
    }

    private static void GuardadoGeneral() {
        Gestor.guardarEspecialistas();
        Gestor.guardarPacientes();
    }


    public static void leer(){
        if(Gestor.PacienteLogExist()&&Gestor.EspecialistaLogExist()) {
            try {
                csvReadE();
                csvReadP();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("File not Found, Please contact a support");
        }
    }
    public static void ingreso() {
        leer();
        String rut =IngresarRut();
        Paciente p=Paciente.buscarPorRut(rut);
        if(p !=null){
            System.out.println("Bienvenido "+p.getNombre());
        }else {
            System.out.println("Quiere crear un perfil de paciente?");
            System.out.println("1)Si \n2)No ");
            if(get1o2()){
                crearNuevoPaciente(rut);
            }else{
                System.out.println("estos son los horarios disponibles *le muestra los horarios*");
            }
        }
        repeat();
        GuardadoGeneral();
    }

    private static void repeat() {
        int x;
        do{
            showMenu();
            System.out.println("desea realizar otra acción en el menu? (No (0) / Si (1))");
            x=validar(1);
        }while (x!=0);
    }

    private static void showMenu() {
        System.out.println("Menu");
        System.out.println("---------------");
        System.out.println("1)Agendar hora con un especialista");
        System.out.println("2)SALIR");
        menu1();
    }

    private static void menu1() {
        if (get1o2()) {
            System.out.println("Ingrese su Prevision");
            System.out.println("[1]Fonasa");
            System.out.println("[2]Isapre");
            menu2();
        }
    }

    private static void menu2() {
        if (get1o2()){
            int index=Especialista.mostrarEspecialistasFonasa();
            Especialista.consultarHoras(Especialista.especialistas.get(index));
        }else{
            int index=Especialista.mostrarEspecialistasIsapre();
            Especialista.consultarHoras(Especialista.especialistas.get(index));
        }
    }

    private static void crearNuevoPaciente(String rut) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Ingrese su Nombre");
        String nombre = sc.next();
        System.out.println("Ingrese su Apellido");
        String apellido = sc.next();
        System.out.println("Quiere proceder el registro con el siguiente rut?: "+ rut);
        System.out.println("1)Si \n2)No ");
        if(get1o2()){
            Paciente p =new Paciente(nombre,apellido,rut);
            Paciente.pacientes.add(p);
        }else{
            String rut2=IngresarRut();
            Paciente p =new Paciente(nombre,apellido,rut2);
            Paciente.pacientes.add(p);
        }
    }

    private static boolean get1o2() {
        return validar(2) == 1;
    }

    public static String IngresarRut() {
        Scanner teclado = new Scanner(System.in);
        String rut;
        do {
            System.out.println("Ingresar rut:");
            rut = teclado.next();

        }while (!Paciente.validarRut(rut));
        return rut.replace("-", "").replace(".","");
    }
    public static void csvReadE() throws FileNotFoundException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        var builder = new CSVReaderBuilder(new FileReader(rutaE));
        try (CSVReader reader = builder.withCSVParser(parser).withSkipLines(1).build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String nombre = nextLine[1];
                String especialidad = nextLine[2];
                boolean fonasa = Boolean.parseBoolean(nextLine[3]);
                boolean isapre = Boolean.parseBoolean(nextLine[4]);
                Especialista.especialistas.add(new Especialista(id,nombre,especialidad,fonasa,isapre));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }
    public static void csvReadP() throws FileNotFoundException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        var builder = new CSVReaderBuilder(new FileReader(rutaP));
        try (CSVReader reader = builder.withCSVParser(parser).withSkipLines(1).build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String nombre = nextLine[0];
                String apellido = nextLine[1];
                String rut =nextLine[2];
                String especialista = nextLine[3];
                String fecha =nextLine[4];
                String fecha2 =nextLine[5];
                Paciente.pacientes.add(new Paciente(nombre,apellido,rut,especialista,fecha,fecha2));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public static int validar(int x) {
        int n = -1;
        do {
            //Scanner ponerlo dentro del DO, y dentro de una función
            Scanner teclado = new Scanner(System.in);
            //System.out.println("ingrese otro numero");
            try {
                n = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error! Por favor, >Ingrese un número<");
            }
            if (n < 0 || n > x) {
                System.out.println("Ingrese un numero válido");
            }
        } while (n < 0 || n > x);
        return n;
    }
}
