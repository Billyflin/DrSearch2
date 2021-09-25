import org.apache.commons.text.WordUtils;

import java.util.ArrayList;

public class Especialista {
    public static ArrayList<Especialista> especialistas=new ArrayList<>();
    private final int id;
    private final String nombre;
    private final String especialidad;
    private final boolean fonasa;
    private final boolean isapre;

    public Especialista(int id, String nombre, String especialidad, boolean fonasa, boolean isapre) {
        this.id = id;
        this.nombre = WordUtils.capitalize(nombre);
        this.especialidad = especialidad;
        this.fonasa = fonasa;
        this.isapre = isapre;
    }

    public static int mostrarEspecialistasFonasa() {
        int counter = 0;
        System.out.println("Profesionales por indice");
        for (Especialista e:especialistas
             ) {
            if(e.isFonasa()){
                ++counter;
                System.out.println();
                System.out.println("Indice: "+counter);
                e.show();
            }
        }
        return Init.validar(counter);
    }

    public static int mostrarEspecialistasIsapre() {
        int counter = 0;
        System.out.println("Profesionales por indice");
        for (Especialista e:especialistas
        ) {
            if(e.isIsapre()){
                ++counter;
                System.out.println();
                System.out.println("Indice: "+counter);
                e.show();
            }
        }
        return Init.validar(counter);
    }

    public static void consultarHoras(Especialista especialista) {
        System.out.println("Aquí irán las horas del especialista Cuando se me ocurra como hacerlas");
    }

    private void show() {
        System.out.println("-------------------");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Especialidad: "+this.especialidad);
        System.out.println("-------------------");
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isFonasa() {
        return fonasa;
    }

    public boolean isIsapre() {
        return isapre;
    }

    @Override
    public String toString() {
        return id +","+nombre +","+especialidad +","+fonasa+","+isapre;
    }
}
