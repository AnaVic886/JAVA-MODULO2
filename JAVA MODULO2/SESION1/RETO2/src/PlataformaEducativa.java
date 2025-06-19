import java.util.List;
import java.util.function.Predicate;

public class PlataformaEducativa {
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object o : lista) {
            if (o instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) o;
                e.marcarRevisado();
                System.out.println("✅ Ejercicio '" + e.titulo + "' marcado como revisado.");
            }
        }
    }

    // Desafío adicional: filtro por autor con Predicate
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("\n🔍 Filtrando materiales por autor:");
        for (MaterialCurso m : lista) {
            if (filtro.test(m)) {
                m.mostrarDetalle();
            }
        }
    }

}