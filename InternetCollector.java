import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.net.ssl.HttpsURLConnection;

public class InternetCollector {

    // �ļ�����·�����ɹ��캯������
    String filePath;
    // ����  
    private static final String ECODING = "UTF-8";  
    // ��ȡimg��ǩ����  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    // ��ȡsrc·��������  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)"; 

    InternetCollector(String filePath) {
        this.filePath = filePath;
    }

    //��ȡһ���ļ���html
    private String getHtml(String filepath) throws Exception {

        if (filepath == null) return null; //�ļ������ڣ��޷���ȡhtml��ֱ�ӷ���null

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            while ((line = br.readLine()) != null ) {
                sb.append(new String(buf, ECODING)); //������� 
                sb.append(line); 
            }
        }
        return sb.toString();
    }

    //��ȡһ�����ӵ�html
    private String getHTML(String url) throws Exception {
        URLConnection connection = getConnection(url);

        if (connection == null) return null; //����ʧ�ܣ��޷���ȡhtml��ֱ�ӷ���null

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

    //����һ��html�ļ��е�����ͼƬ��Դ����(http.....jpg/gif/peng)
    private List<String> getImageUrl(String html) throws Exception {

        if (html == null) return null; //htmlΪnullʱֱ�ӷ���null

        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>(); 

        while (matcher.find()) {
            String matches = matcher.group();
            if (matches.contains("src")) {
                int index1 = matches.indexOf("src");
                int index2 = matches.indexOf("\"", index1 + 5); //src="http.....",index1 + 5 -> 'h'
                if (index1 > 0 && (index2 > index1)) { //��ƥ�䵽
                    String matches1 = matches.substring(index1 + 5, index2);
                    if (matches1.contains("http") && matches1.contains("gif"))
                        listImgUrl.add(matches1);
                }  
            }  
        }  
        return listImgUrl; 
    }

    //������ȡһ��html�����е�����
    private List<String> getImageSrc(String html) {

        if (html == null) return null; //htmlΪnullʱֱ�ӷ���null���޷���ȡ���κ�����

        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>();  

        while (matcher.find()) {
            String matches = matcher.group();
            //��ͼƬ��Դ��js��Դ���ӳ�ȥ(���Ե����ָ���һ��������boolean nonHtmlLink(String matches)
            if (matches.contains("jpg") || matches.contains("gif") || matches.contains("png")
                || matches.contains("js")) continue;
            //��Ч���������ٰ���com��org,��ȥ���硰http://��֮�����Ч����
            if (matches.contains("com") || matches.contains("org")) {
                //��ȥβ���ĵ����ź�˫����(������ڵõ�����ʱ�ͳ�ȥ���ǣ�)
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

    //������ȡһ��html�����е�ͼƬ��Դ����
    private List<String> getImageUrl1(String html) {

        if (html == null) return null;

        Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(html);  
        List<String> listImgUrl = new ArrayList<>();  
        while (matcher.find()) {
            String matches = matcher.group();
            if (matches.contains("com") || matches.contains("org")) {
                //��ȥβ���ĵ����ź�˫����
                if (matches.contains("\"")) {
                    int index = matches.lastIndexOf("\"");
                    String str = matches.substring(0, index);
                    //���ж������Ƚϸ��ӣ��ɵ����ָ���һ������
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

    //��ȡ��ĳһ��Դ��(�ɹ�)����,http
    private HttpURLConnection getConnection(String url_) {
        try {
            URL url = new URL(url_);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection(); //openConnection()���ص���URLConnection��Http~�ĸ���
            int responsecode = urlConnection.getResponseCode(); //HttpURLConnection�еķ���
            if(responsecode == 200) {
                System.out.println("this url connect success!");
                return urlConnection; //���سɹ�������
            } else {
                System.out.println("this url connect fails!");
                return null; //���ɹ�����null
            }
        } catch (Exception e) {
            e.printStackTrace(); //��ӡ������Ϣ
            return null; //�����쳣Ҳ�Ƿ���null,��Ҫ�������ж�
        }   
    }

    //https����
    private HttpURLConnection getHttpsConnection(String url_) throws Exception{
        try {
            URL url = new URL(url_);
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection(); //openConnection()���ص���URLConnection��Http~�ĸ���
            int responsecode = urlConnection.getResponseCode(); //HttpURLConnection�еķ���
            if(responsecode == 200) {
                System.out.println("this url connect success!");
                return urlConnection; //���سɹ�������
            } else {
                System.out.println("this url connect fails!");
                return null; //���ɹ�����null
            }
        } catch (Exception e) {
            e.printStackTrace(); //��ӡ������Ϣ
            return null; //�����쳣Ҳ�Ƿ���null,��Ҫ�������ж�
        }   
    }

    //url--����ͼƬ��·�������ص���ͼƬ
    private boolean download(String url) throws Exception {
        HttpURLConnection urlConnection = getConnection(url); //��ȡ����

        if (urlConnection == null) return false; //�޷���ȡ���ӣ�����ʧ��

        UUID u = UUID.randomUUID(); //��һ�޶����ļ���
        //ͨ���ַ���ȡͼƬ����ʾ��ʱ��������
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
                    writer.write(bytes, 0, r); //�����������Ķ���
                writer.flush(); 
            }

            return true;       
    }

    //���Եݹ�ʽ����
    //------link(URL)
    //------html(images)
    //------------link1(list)
    //------------html(images1)
    //-----------------link2(list1)
    //-----------------html(images2)
    public void test() throws Exception {
        List<String> list = getImageSrc(getHTML(URL));
        //����ͼƬ
        List<String> images = getImageUrl(getHTML(URL));
        if (images != null)
            images.forEach(System.out :: println);
        //����һ������
        for (String str : list) {
            //����Ѱ�Ҷ�������
            List<String> list1 = getImageSrc(getHTML(str));
            if (list1 == null) continue; //���ҳ����û������������
            //��һ��������������ͼƬ
            List<String> images1 = getImageUrl(getHTML(str));
            if (images1 == null) continue; //�����������û��ͼƬ
            images1.forEach(System.out :: println);
            //������������
            for (String str1 : list1) {
                //�Ӷ���������������ͼƬ
                 List<String> images2 = getImageUrl(getHTML(str1));
                 if (images2 == null) continue; //�����������û��ͼƬ
                 images2.forEach(System.out :: println);
            }
        }
    }

    //urlΪһ�����ӣ���ȡһ�������µ�����ͼƬ
    public void test1(String url)  throws Exception {
        String HTML = getHtml(url); //��ȡhtml
        List<String> images = getImageUrl1( HTML ); //������ȡͼƬ��ַ
        if (images != null)
            for (String image : images) 
                if (download(image)) System.out.printf("download \"%s\" successes!", image);
    }

    //imageΪͼƬ��ַ��ֱ������Ŀ����Դ��������
    public void test2(String image) throws Exception {
         if (download(image)) System.out.printf("download \"%s\" successes!", image);
    }

    //��ӡһ��html�µ�����ͼƬ��Դ����,���������һ��html�ļ�·��
    public void test3(String filepath) throws Exception {
        String str = getHtml(filepath);
        System.out.printf("file size: %d KB\n", str.length() / 1024);
        List<String> list = getImageUrl1(str);
        list.forEach(System.out::println);
    }

    //����media�ļ�
    public void test4() throws Exception {
        URL url = new URL("http://m10.music.126.net/20161109081813/b4d392d1db5976dd2e751a1185979d93/ymusic/1389/136c/a2ba/36e22892ea4f4e1e8e98a6a652592ec8.mp3");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        int responseCode = connection.getResponseCode();
        System.out.println("responsecode: " + responseCode);
    }

    public static void main(String args[]) throws Exception {
        String targetPath = "G:\\˹�Ƶ���ȿռ�\\YͼƬ��\\test";
        InternetCollector coll = new InternetCollector(targetPath);
        //coll.download("http://ww4.sinaimg.cn/bmiddle/a6a976a2tw1epr6eob0y2g20a207kaz2.gif");
       //coll.test1("C:\\Users\\chongxue\\Desktop\\InternetCollector.html");
        coll.test4();
     }

}
