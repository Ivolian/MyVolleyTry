import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class RealLoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        boolean success = judge(username, password);

        try {
            Thread.sleep(2000);

        }catch (Exception e){
            //
        }
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(generateJson(success));
    }

    private static String generateJson(boolean success) {

        Result result = new Result();
        result.setSuccess(success);

        return GsonUtil.toJson(result);
    }

    private static boolean judge(String username, String password) {

        return username.equals("admin") && password.equals("123456");
    }

}  