package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDate {

    public static String toStringDate() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatterData.format(agora);
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);

        return "" + dataFormatada + " | " + horaFormatada + "";
    }
}
