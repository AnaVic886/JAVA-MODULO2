import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🛣️ Verificando pista...");
            simularLatencia();
            boolean disponible = Math.random() < 0.8;
            System.out.println("🛣️ Pista disponible: " + disponible);
            return disponible;
        }).exceptionally(e -> {
            System.out.println("❌ Error al verificar pista: " + e.getMessage());
            return false;
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🌦️ Verificando clima...");
            simularLatencia();
            boolean favorable = Math.random() < 0.8;
            System.out.println("🌦️ Clima favorable: " + favorable);
            return favorable;
        }).exceptionally(e -> {
            System.out.println("❌ Error al verificar clima: " + e.getMessage());
            return false;
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚦 Verificando tráfico aéreo...");
            simularLatencia();
            boolean despejado = Math.random() < 0.9;
            System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
            return despejado;
        }).exceptionally(e -> {
            System.out.println("❌ Error al verificar tráfico aéreo: " + e.getMessage());
            return false;
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("👷‍♂️ Verificando personal en tierra...");
            simularLatencia();
            boolean disponible = Math.random() < 0.95;
            System.out.println("👷‍♂️ Personal disponible: " + disponible);
            return disponible;
        }).exceptionally(e -> {
            System.out.println("❌ Error al verificar personal en tierra: " + e.getMessage());
            return false;
        });
    }

    private void simularLatencia() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
        } catch (InterruptedException e) {
            throw new RuntimeException("Simulación interrumpida");
        }
    }

    public void procesarAterrizaje() {
        System.out.println("\n🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenRun(() -> {
                    boolean condicionesOptimas = pista.join() && clima.join() && trafico.join() && personal.join();
                    if (condicionesOptimas) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                });
    }

    public static void main(String[] args) {
        AeropuertoControl control = new AeropuertoControl();
        control.procesarAterrizaje();

        try {
            TimeUnit.SECONDS.sleep(10); // Espera para que las tareas finalicen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}