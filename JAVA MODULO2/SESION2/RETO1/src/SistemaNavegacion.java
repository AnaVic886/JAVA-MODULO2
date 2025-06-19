import java.util.concurrent.Callable;

public class SistemaNavegacion implements Callable<String>{
    public String call() throws Exception {
        Thread.sleep(1500); // Simula tiempo de procesamiento
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}
