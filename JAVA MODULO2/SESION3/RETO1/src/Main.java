import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Mario", "domicilio", "555-1234"),
                new Pedido("Natalia", "local", "615-5721"),
                new Pedido("Liliana", "domicilio", null),
                new Pedido("Karen", "domicilio", "612-8978")
        );


        pedidos.stream()
                .filter(pedido -> "domicilio".equalsIgnoreCase(pedido.getTipoEntrega()))
                .map(Pedido::getTelefono)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(telefono -> "📞 Confirmación enviada al número: " + telefono)
                .forEach(System.out::println);
    }
}