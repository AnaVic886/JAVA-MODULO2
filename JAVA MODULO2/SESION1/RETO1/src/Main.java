import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipos = new ArrayList<>();
        ordenesPrototipos.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipos.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        System.out.println("📋 Órdenes registradas:");
        GestorOrdenes.mostrarOrdenes(ordenesMasa);

        System.out.println("\n📋 Órdenes registradas:");
        GestorOrdenes.mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("\n📋 Órdenes registradas:");
        GestorOrdenes.mostrarOrdenes(ordenesPrototipos);

        GestorOrdenes.procesarPersonalizadas(ordenesPersonalizadas, 200);

        // Para contar todas, combinamos todas las listas
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipos);

        GestorOrdenes.contarOrdenes(todas);
    }
}