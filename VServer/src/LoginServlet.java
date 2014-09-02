import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        process(httpServletRequest, httpServletResponse);
    }

    public void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        // 获取参数
//        String username = httpServletRequest.getParameter("username");

        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(generateJson());
    }

    private String generateJson() {

        List<DownloadInfo> downloadInfoList = new ArrayList<DownloadInfo>();
        for (int i = 1; i <= 10; i++) {
            downloadInfoList.add(
                    new DownloadInfo(
                            "name" + i,
                            "/jpg/i" + i + ".jpg",
                            "description" + i,
                            i
                    )
            );
        }

        return GsonUtil.toJson(downloadInfoList);
    }

}  