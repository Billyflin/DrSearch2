import org.apache.commons.text.WordUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Paciente {
    public static ArrayList<Paciente> pacientes=new ArrayList<>();
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String nombre;
    private final String apellido;
    private final String rut;
    private String especialista;
    private final boolean rutValido;
    private LocalDate fechaConsulta;
    private LocalDate fechaDePedido;

    public Paciente(String nombre, String apellido, String rut, String especialista, String fecha,String fecha2) {//escritura desde CSV
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.especialista = especialista;
        this.rutValido = validarRut(rut);
        try {
            this.fechaConsulta = LocalDate.parse(fecha, formato);
        } catch (Exception e) {
            this.fechaConsulta = null;
        }
        try{
            this.fechaDePedido= LocalDate.parse(fecha2,formato);
        } catch (Exception e) {
            this.fechaConsulta = null;
        }
    }

    public Paciente(String nombre, String apellido, String rut) {//runtime constructor
        this.nombre = WordUtils.capitalize(nombre);
        this.apellido = WordUtils.capitalize(apellido);
        this.rut = rut;
        this.rutValido = validarRut(rut);
        this.fechaDePedido = LocalDate.parse(LocalDate.now().format(formato),formato);
    }
    public static Paciente buscarPorRut(String rut){
        for (Paciente p:pacientes
             ) {
            if(rut.equals(p.rut)){
                if(p.nombre.equals("Billy")){
                    System.out.println(yamete());
                }
                return p;
            }
        }
        return null;
    }

    public static String yamete() {
        return "⠄⠄⠄⢰⣧⣼⣯⠄⣸⣠⣶⣶⣦⣾⠄⠄⠄⠄⡀⠄⢀⣿⣿⠄⠄⠄⢸⡇⠄⠄\n" + " ⠄⠄⠄⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄⠄⠸⢀⣿⠄\n" + "⠄⠄⢀⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄⣀⣥⣿⣿⠄\n" + "⠄⠄⢸⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿⠄\n" + "⠄⢀⢸⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀⣤⣄⠉⠋⣰\n" + "⠄⣼⣖⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿⣿⣿⠇⢀⣤\n" + "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣶⣥⣴⣿⡗\n" + "⢀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄\n" + "⢸⣿⣦⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄\n" + "⠘⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠄⠹⠈⢋⣽⣿⣿⣿⣿⣵⣾⠃⠄\n" + "⠄⠘⣿⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿⣿⣿⠃⠄⠄\n" + "⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛⠛⠁⠄⠄⠄\n" + "⠄⠄⠄⠄⠈⠛⢿⣿⣿⣿⠁⠞⢿⣿⣿⡄⢿⣿⡇⣸⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄\n" + "⠄⠄⠄⠄⠄⠄⠄⠉⠻⣿⣿⣾⣦⡙⠻⣷⣾⣿⠃⠿⠋⠁⠄⠄⠄⠄⠄⢀⣠⣴\n" + "⣿⣿⣿⣶⣶⣮⣥⣒⠲⢮⣝⡿⣿⣿⡆⣿⡿⠃⠄⠄⠄⠄⠄⠄⠄⣠⣴⣿⣿⣿";
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public String getEspecialista() {
        return especialista;
    }

    public boolean isRutValido() {
        return rutValido;
    }

    public String getFechaConsultaStr() {
        try{
            return fechaConsulta.format(formato);
        }catch(NullPointerException e) {
            return "";
        }
    }

    public String getFechaDePedidoStr() {
        try {
            return fechaDePedido.format(formato);
        }catch(NullPointerException e) {
            return "";
        }
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }
    public LocalDate getFechaDePedido() {
        return fechaDePedido;
    }
    public static void showAllPacients(){
        for (Paciente p:
             pacientes) {
            p.show();
        }
    }

    private void show() {
        System.out.println("------------------");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Apellido: "+this.apellido);
        System.out.println("Rut: "+this.rut);
        System.out.println("RutValido: "+this.rutValido);
        System.out.println("Fecha Próxima Agendada: "+this.fechaConsulta);
        System.out.println("Fecha Realizada: "+this.fechaDePedido);
        System.out.println("------------------");
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


    @Override
    public String toString() {
        return nombre+","+ apellido +","+rut+","+especialista+","+rutValido+","+getFechaConsultaStr()+","+getFechaDePedidoStr();
        }
    }


