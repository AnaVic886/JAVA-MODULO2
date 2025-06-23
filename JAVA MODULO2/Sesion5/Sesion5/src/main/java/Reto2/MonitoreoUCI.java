package Reto2;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class MonitoreoUCI {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        // Simula flujo de un paciente
        Flux<String> paciente1 = crearFlujoPaciente(1);
        Flux<String> paciente2 = crearFlujoPaciente(2);
        Flux<String> paciente3 = crearFlujoPaciente(3);

        // Fusionar flujos de pacientes
        Flux.merge(paciente1, paciente2, paciente3)
                .subscribe(System.out::println);

        // Mantener la app en ejecución por 60 segundos
        Thread.sleep(60_000);
    }

    private static Flux<String> crearFlujoPaciente(int idPaciente) {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> generarSignosVitalesCriticos(idPaciente))
                .filter(alerta -> !alerta.isEmpty()); // Solo pasar eventos críticos
    }

    private static String generarSignosVitalesCriticos(int pacienteId) {
        int fc = random.nextInt(160 - 40) + 40;
        int paSys = random.nextInt(180 - 80) + 80;
        int paDia = random.nextInt(120 - 50) + 50;
        int spo2 = random.nextInt(100 - 80) + 80;

        StringBuilder alerta = new StringBuilder();

        if (fc < 50 || fc > 120) {
            alerta.append("⚠️ Paciente ").append(pacienteId).append(" - FC crítica: ").append(fc).append(" bpm\n");
        }

        if (paSys < 90 || paDia < 60 || paSys > 140 || paDia > 90) {
            alerta.append("⚠️ Paciente ").append(pacienteId)
                    .append(" - PA crítica: ").append(paSys).append("/").append(paDia).append(" mmHg\n");
        }

        if (spo2 < 90) {
            alerta.append("⚠️ Paciente ").append(pacienteId).append(" - SpO2 baja: ").append(spo2).append("%\n");
        }

        return alerta.toString().trim();
    }
}
