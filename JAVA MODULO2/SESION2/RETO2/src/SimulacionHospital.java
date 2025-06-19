import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        String[] profesionales = {
                "Dra. Sánchez", "Dr. Gómez", "Dra. Velasco", "Dra. Miceli", "Dr. Trujillo"
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String nombre : profesionales) {
            Runnable tarea = () -> salaCirugia.usar(nombre);
            executor.execute(tarea);
        }

        executor.shutdown();
    }
}