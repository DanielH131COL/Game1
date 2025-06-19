package github.com.danielh131col;

import java.util.ArrayList;
import java.util.List;

public class TiemposGuardados {
    public static final List<String> mejoresTiempos = new ArrayList<>();

    public static void agregarTiempo(String tiempo) {
        mejoresTiempos.add(tiempo);
        if (mejoresTiempos.size() > 5) {
            mejoresTiempos.remove(0);
        }
    }
}