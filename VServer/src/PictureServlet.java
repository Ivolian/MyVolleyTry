import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PictureServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        int number = Integer.valueOf(httpServletRequest.getParameter("number"));

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(generateJson(number));
    }

    private static String generateJson(int number) {

        List<PictureInfo> pictureInfoList = new ArrayList<PictureInfo>();
        while (number > 0) {
            for (int i = 1; i <= 5; i++) {
                pictureInfoList.add(
                        new PictureInfo(
                                "nature" + i,
                                "/jpg/img_nature" + i + ".jpg"
                        )
                );
            }
            number--;
        }

        return GsonUtil.toJson(pictureInfoList);
    }

}