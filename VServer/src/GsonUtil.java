import com.google.gson.Gson;


public class GsonUtil {

    private static Gson instance = new Gson();

    public static Gson getInstance() {

        return instance;
    }

    public static String toJson(Object src) {

        return instance.toJson(src);
    }

}
