package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {
    /**
     * @param content source code
     * @return a list of links
     */
    public List<String> parseUrls(String content) {
        // 爬虫的一个环节，解析html，找出url
        // 一个文本处理题
        // 正好是一个java io处理题
        List<String> urls = new ArrayList<String>();
        if (content == null) {
            return urls;
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("\\s+");
		matcher = pattern.matcher(content);
        String contentFormat = matcher.replaceAll(" ");
        String[] lines = contentFormat.split(">");
        if (lines == null) {
            return urls;
        }
        
        pattern =
                Pattern.compile(".*\"((http(s)?://)?(www\\.)?[0-9A-z_-]+(\\.[A-z/]+))\".*", Pattern.CASE_INSENSITIVE);
        for (int i = 0, len = lines.length; i < len; i++) {
//            System.out.println(lines[i]);
            matcher = pattern.matcher(lines[i]);
            if (!matcher.matches()) {
                continue;
            }
            if(matcher.matches()){
                urls.add(matcher.group(1));
            }
        }
        return urls;
    }
    public static void main(String[] args) {
    	UrlParser parser = new UrlParser();
    	String content = "<html>\n"
    				+ "\n<a href=\"http://www.google.com\" class=\"text-lg\">Google</a>"
    				+ "\n<a href=\"https://facebook.com\" style=\"display:none\">Facebook</a>"
    				+ "\n</div>"
    				+ "<a href=\"www.linkedin.com\">Linkedin</a>"
    				+ "\n<a href = \"http://github.io\">LintCode</a>"
    				+ "\n</body>"
    				+ "\n</html>";
    	List<String> urls = parser.parseUrls(content);
    	System.out.println("resust:" + urls);
    }
}
