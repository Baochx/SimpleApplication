import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.net.ssl.HttpsURLConnection;

public class InternetCollector {

    // 文件保存路径，由构造函数传入
    String filePath;
    // 编码  
    private static final String ECODING = "UTF-8";  
    // 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    // 获取src路径的正则  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)"; 

    InternetCollector(String filePath) {
        this.filePath = filePath;
    }

    //获取一个文件的html
    private String getHtml(String filepath) throws Exception {

        if (filepath == null) return null; //文件不存在，无法获取html，直接返回null

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            while ((line = br.readLine()) != null ) {
                sb.append(new String(buf, ECODING)); //如需解码 
                sb.append(line); 
            }
        }
        return sb.toString();
    }

    //获取一个链接的html
    private String getHTML(String url) throws Exception {
        URLConnection connection = getConnection(url);

        if (connection == null) return null; //连接失败，无法获取html，直接返回null

        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];  
        int length = 0;  
        StringBuilder sb = new StringBuilder();  
        while ((length = in.read(buf, 0, buf.length)) > 0) {  
            sb.append(new String(buf, ECODING));  
        }  
        in.close();  
        return sb.toString();  
    }

    //解析一个html文件中的所有图片资源链接(http.....jpg/gif/peng)
    private List<String> getImageUrl(String html) throws Exception {

        if (html == null) return null; //html为null时直接返回null

        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>(); 

        while (matcher.find()) {
            String matches = matcher.group();
            if (matches.contains("src")) {
                int index1 = matches.indexOf("src");
                int index2 = matches.indexOf("\"", index1 + 5); //src="http.....",index1 + 5 -> 'h'
                if (index1 > 0 && (index2 > index1)) { //能匹配到
                    String matches1 = matches.substring(index1 + 5, index2);
                    if (matches1.contains("http") && matches1.contains("gif"))
                        listImgUrl.add(matches1);
                }  
            }  
        }  
        return listImgUrl; 
    }

    //解析获取一个html中所有的链接
    private List<String> getImageSrc(String html) {

        if (html == null) return null; //html为null时直接返回null，无法获取到任何链接

        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>();  

        while (matcher.find()) {
            String matches = matcher.group();
            //把图片资源和js资源链接除去(可以单独分隔出一个方法：boolean nonHtmlLink(String matches)
            if (matches.contains("jpg") || matches.contains("gif") || matches.contains("png")
                || matches.contains("js")) continue;
            //有效链接中至少包含com和org,除去诸如“http://”之类的无效链接
            if (matches.contains("com") || matches.contains("org")) {
                //除去尾部的单引号和双引号(如何能在得到链接时就除去它们？)
                if (matches.contains("\"")) {
                    int index = matches.lastIndexOf("\"");
                    listImgUrl.add(matches.substring(0, index)); 
                } else if (matches.contains("\'")) {
                    int index = matches.lastIndexOf("\'");
                    listImgUrl.add(matches.substring(0, index)); 
                } else listImgUrl.add(matches);
            }
        }  
        return listImgUrl;
    }

    //解析获取一个html中所有的图片资源链接
    private List<String> getImageUrl1(String html) {

        if (html == null) return null;

        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>();  
        while (matcher.find()) {
            String matches = matcher.group();
            if (matches.contains("com") || matches.contains("org")) {
                //除去尾部的单引号和双引号
                if (matches.contains("\"")) {
                    int index = matches.lastIndexOf("\"");
                    String str = matches.substring(0, index);
                    //如判断条件比较复杂，可单独分隔出一个方法
                    if (str.endsWith(".gif"))
                        listImgUrl.add(str); 
                } else if (matches.contains("\'")) {
                    int index = matches.lastIndexOf("\'");
                    String str = matches.substring(0, index);
                    if (str.endsWith(".gif"))
                        listImgUrl.add(str); 
                } else {
                    if (matches.endsWith(".gif"))
                        listImgUrl.add(matches);
                }
            }
        }  
        return listImgUrl;
    }

    //获取到某一资源的(成功)连接,http
    private HttpURLConnection getConnection(String url_) {
        try {
            URL url = new URL(url_);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection(); //openConnection()返回的是URLConnection，Http~的父类
            int responsecode = urlConnection.getResponseCode(); //HttpURLConnection中的方法
            if(responsecode == 200) {
                System.out.println("this url connect success!");
                return urlConnection; //返回成功的连接
            } else {
                System.out.println("this url connect fails!");
                return null; //不成功返回null
            }
        } catch (Exception e) {
            e.printStackTrace(); //打印出错信息
            return null; //发生异常也是返回null,不要在这里中断
        }   
    }

    //https连接
    private HttpURLConnection getHttpsConnection(String url_) throws Exception{
        try {
            URL url = new URL(url_);
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection(); //openConnection()返回的是URLConnection，Http~的父类
            int responsecode = urlConnection.getResponseCode(); //HttpURLConnection中的方法
            if(responsecode == 200) {
                System.out.println("this url connect success!");
                return urlConnection; //返回成功的连接
            } else {
                System.out.println("this url connect fails!");
                return null; //不成功返回null
            }
        } catch (Exception e) {
            e.printStackTrace(); //打印出错信息
            return null; //发生异常也是返回null,不要在这里中断
        }   
    }

    //url--单张图片的路径，下载单张图片
    private boolean download(String url) throws Exception {
        HttpURLConnection urlConnection = getConnection(url); //获取连接

        if (urlConnection == null) return false; //无法获取链接，下载失败

        UUID u = UUID.randomUUID(); //独一无二的文件名
        //通过字符读取图片在显示的时候有问题
        try ( /*BufferedWriter writer = 
                    new BufferedWriter(
                        new OutputStreamWriter(
                            new FileOutputStream(
                                new File(filePath + "/" +  u + ".gif"))) );
            BufferedReader reader = 
                    new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()))*/ 
            DataOutputStream writer = new DataOutputStream(new FileOutputStream(filePath + "/" +  u + ".gif"));
            InputStream reader = urlConnection.getInputStream();
            )
            {
                byte[] bytes = new byte[1024];
                int r = 0;
                while((r = reader.read(bytes, 0, 1024)) != -1) 
                    writer.write(bytes, 0, r); //避免读进多余的东西
                writer.flush(); 
            }

            return true;       
    }

    //测试递归式搜素
    //------link(URL)
    //------html(images)
    //------------link1(list)
    //------------html(images1)
    //-----------------link2(list1)
    //-----------------html(images2)
    public void test() throws Exception {
        List<String> list = getImageSrc(getHTML(URL));
        //搜索图片
        List<String> images = getImageUrl(getHTML(URL));
        if (images != null)
            images.forEach(System.out :: println);
        //遍历一级链接
        for (String str : list) {
            //继续寻找二级链接
            List<String> list1 = getImageSrc(getHTML(str));
            if (list1 == null) continue; //这个页面再没有其它的链接
            //从一级链接里面搜索图片
            List<String> images1 = getImageUrl(getHTML(str));
            if (images1 == null) continue; //这个链接里面没有图片
            images1.forEach(System.out :: println);
            //遍历二级链接
            for (String str1 : list1) {
                //从二级链接里面搜索图片
                 List<String> images2 = getImageUrl(getHTML(str1));
                 if (images2 == null) continue; //这个链接里面没有图片
                 images2.forEach(System.out :: println);
            }
        }
    }

    //url为一个链接，获取一个链接下的所有图片
    public void test1(String url)  throws Exception {
        String HTML = getHtml(url); //获取html
        List<String> images = getImageUrl1( HTML ); //解析获取图片地址
        if (images != null)
            for (String image : images) 
                if (download(image)) System.out.printf("download \"%s\" successes!", image);
    }

    //image为图片地址，直接连接目标资源进行下载
    public void test2(String image) throws Exception {
         if (download(image)) System.out.printf("download \"%s\" successes!", image);
    }

    //打印一个html下的所有图片资源链接,传入参数是一个html文件路径
    public void test3(String filepath) throws Exception {
        String str = getHtml(filepath);
        System.out.printf("file size: %d KB\n", str.length() / 1024);
        List<String> list = getImageUrl1(str);
        list.forEach(System.out::println);
    }

    //下载media文件
    public void test4() throws Exception {
        URL url = new URL("http://m10.music.126.net/20161109081813/b4d392d1db5976dd2e751a1185979d93/ymusic/1389/136c/a2ba/36e22892ea4f4e1e8e98a6a652592ec8.mp3");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        int responseCode = connection.getResponseCode();
        System.out.println("responsecode: " + responseCode);
    }

    public static void main(String args[]) throws Exception {
        String targetPath = "G:\\斯云的异度空间\\Y图片库\\test";
        InternetCollector coll = new InternetCollector(targetPath);
        //coll.download("http://ww4.sinaimg.cn/bmiddle/a6a976a2tw1epr6eob0y2g20a207kaz2.gif");
       //coll.test1("C:\\Users\\chongxue\\Desktop\\InternetCollector.html");
        coll.test4();
     }

}
