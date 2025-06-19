import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();


        Video v1 = new Video("Introducción a Java", "Mario", 15);
        Video v2 = new Video("POO en Java", "Carlos", 20);


        Articulo a1 = new Articulo("Historia de Java", "Ana", 1200);
        Articulo a2 = new Articulo("Tipos de datos", "Luis", 800);


        Ejercicio e1 = new Ejercicio("Variables y tipos", "Luis");
        Ejercicio e2 = new Ejercicio("Condicionales", "Mario");

        materiales.add(v1);
        materiales.add(v2);
        materiales.add(a1);
        materiales.add(a2);
        materiales.add(e1);
        materiales.add(e2);


        PlataformaEducativa.mostrarMateriales(materiales);


        List<Video> videos = List.of(v1, v2);
        PlataformaEducativa.contarDuracionVideos(videos);


        PlataformaEducativa.marcarEjerciciosRevisados(materiales);

        PlataformaEducativa.filtrarPorAutor(materiales, m -> m.getAutor().equals("Mario"));
    }
}