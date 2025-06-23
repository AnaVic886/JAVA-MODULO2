package Reto1;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger redLightCounter = new AtomicInteger(0);

        // 🚗 Sensor de tráfico (con backpressure)
        Flux<String> trafficSensorFlux = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0-100%
                .onBackpressureBuffer(10)
                .filter(congestion -> congestion > 70)
                .map(congestion -> "🚗 Alerta: Congestión del " + congestion + "% en Avenida Solar")
                .doOnNext(System.out::println);

        // 🌫️ Contaminación del aire
        Flux<String> airPollutionFlux = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(100)) // PM2.5
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)")
                .doOnNext(System.out::println);

        // 🚑 Accidentes viales
        Flux<String> trafficAccidentFlux = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    List<String> priorities = Arrays.asList("Baja", "Media", "Alta");
                    return priorities.get(random.nextInt(priorities.size()));
                })
                .filter(priority -> priority.equals("Alta"))
                .map(priority -> "🚑 Emergencia vial: Accidente con prioridad " + priority)
                .doOnNext(System.out::println);

        // 🚝 Trenes maglev (con backpressure)
        Flux<String> maglevTrainFlux = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // 0–10 minutos
                .onBackpressureBuffer(10)
                .filter(delay -> delay > 5)
                .map(delay -> "🚝 Tren maglev con retraso crítico: " + delay + " minutos")
                .doOnNext(System.out::println);

        // 🚦 Semáforos inteligentes
        Flux<String> smartTrafficLightFlux = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    List<String> states = Arrays.asList("Verde", "Amarillo", "Rojo");
                    return states.get(random.nextInt(states.size()));
                })
                .filter(state -> {
                    if ("Rojo".equals(state)) {
                        return redLightCounter.incrementAndGet() >= 3;
                    } else {
                        redLightCounter.set(0);
                        return false;
                    }
                })
                .map(state -> "🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte")
                .doOnNext(System.out::println);

        // 🔔 Alerta global si ocurren 3+ eventos críticos al mismo tiempo
        Flux.combineLatest(
                        trafficSensorFlux.map(x -> true).startWith(false),
                        airPollutionFlux.map(x -> true).startWith(false),
                        trafficAccidentFlux.map(x -> true).startWith(false),
                        maglevTrainFlux.map(x -> true).startWith(false),
                        smartTrafficLightFlux.map(x -> true).startWith(false),
                        array -> Tuples.of(
                                (Boolean) array[0],

                                (Boolean) array[1],
                                (Boolean) array[2],
                                (Boolean) array[3],
                                (Boolean) array[4]
                        )
                )
                .filter(tuple -> Arrays.asList(
                        tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4(), tuple.getT5()
                ).stream().filter(Boolean::booleanValue).count() >= 3)
                .sample(Duration.ofSeconds(1))
                .subscribe(t -> System.out.println("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime"));

        // Mantener aplicación corriendo por 1 minuto
        Thread.sleep(60_000);
    }
}
