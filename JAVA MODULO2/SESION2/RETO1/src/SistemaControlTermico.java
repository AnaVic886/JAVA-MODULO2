import java.util.concurrent.Callable;

public class SistemaControlTermico implements Callable<String>{
    public String call() throws Exception {
        Thread.sleep(1200);
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}
