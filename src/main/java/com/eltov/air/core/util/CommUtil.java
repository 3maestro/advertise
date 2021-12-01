package com.eltov.air.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.SimpleTimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommUtil {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CommUtil.class);
	
	//NullCheck
	public static String getChkNull(String str) {
		String strTmp;
		if (str == null)
			strTmp = "";
		else
			strTmp = str;
		return strTmp.trim();
	}

	public static Float getChkNull(Float num) {
		Float numTmp;
		if (num == null)
			numTmp = -1.0F;
		else
			numTmp = num;
		return numTmp;
	}

	public static Integer getChkNull(Integer num) {
		if (num == null)
			return -1;
		return num;
	}

	public static Double getChkNull(Double num) {
		if (num == null)
			return -1D;
		return num;
	}
	
//    public static Timestamp getChkNull(Timestamp ts) {
//    	if(ts == null) return new Timestamp(-1L);
//    	return ts;
//    }
	public static String getChkValue(Map<String, Object> p_info, String key){
		return getChkValue(p_info,key,""); 
	}
	public static String getChkValue(Map<String, Object> p_info, String key, String defaultValue){
		String value = "";
		if(defaultValue == null) defaultValue = "";
		try {
			if(p_info == null) return defaultValue;
			if(p_info.get(key) == null) {
				value = defaultValue;
			}else {
				value = p_info.get(key) + "";
			}
		}catch(Exception e) {
			return defaultValue;
		}
		return value;
    }
	
	public static String getChkFilePath(Map<String, Object> p_info, String key, String filePath) {
		String fileName = getChkValue(p_info, key);
		if(StringUtils.isBlank(fileName)) {
			return "";
		}else {
			return filePath + fileName;
		}
	}
	
    public static Map<String, Object> getChkNull(Map<String, Object> p_info){
        if(p_info == null) return new HashMap<String, Object>();
        return p_info;
    }
    
    public static List<HashMap<String, Object>> getChkNull(List<HashMap<String, Object>> p_info){
        if(p_info == null) return new ArrayList<HashMap<String, Object>>();
        return p_info;
    }
    
    
//
//    public String getCheckBr(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("\n","<br>");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
//    public String getCheckNoScript(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("(?i)<script","");
//            str = str.replaceAll("(?i)</script>","");
//            str = str.replaceAll("\n","<br>");
//
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
//    public String getCheckNoBr(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("&lt;","<");
//            str = str.replaceAll("&gt;",">");
//            str = str.replaceAll("<br>","");
//            str = str.replaceAll("<BR>","");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
//    public String getCheckNoHtmlScript(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("(?i)<script","");
//            str = str.replaceAll("(?i)</script>","");
//            str = str.replaceAll("(?i)<table","");
//            str = str.replaceAll("(?i)</table>","");
//            str = str.replaceAll("(?i)<tr","");
//            str = str.replaceAll("(?i)</tr>","");
//            str = str.replaceAll("(?i)<td","");
//            str = str.replaceAll("(?i)</td>","");
//            str = str.replaceAll("\n","<br>");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
//    public String getCheckNoHtml(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("<","&lt;");
//            str = str.replaceAll(">","&gt;");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
//
//    public String getCheckEditValue(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll("\"","&#34;");
//            str = str.replaceAll("'","&#39;");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
//
    public static String getChkDateReplace(String str){
        String strTmp;

        if(str == null){
            strTmp = "";
        }else{
            str = str.replaceAll("-","");
            strTmp = str;
        }

        return strTmp.trim();
    }
//
    public static String getChkTimeReplace(String str){
        String strTmp;

        if(str == null){
            strTmp = "";
        }else{
            str = str.replaceAll(":","");
            strTmp = str;
        }

        return strTmp.trim();
    }

    
    //Object -> String 형변환
//    public String getConversion(Object obj) {
//    	
//    	String str = "";
//    	
//    	if(obj == null){
//    		obj = "";
//    	}else {
//    		str = obj.toString();
//    	}
//    	
//    	return str;
//    }
    
    //문자열 앞, 뒤, 사이 공백 제거
    public static String getRemoveSpace(String str) {
    	String rePlaceStr = "";
    	if (str == null) str = "";
//		str.trim();
		rePlaceStr = str.replaceAll("\\p{Z}","");
    	
    	return rePlaceStr;
    }
    
    public static LocalDate getDateString2Date(String dayStr, String pattern) {
    	DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(dayStr, df);
    }
    
    public static String getTime(String pattern) {
    	if(StringUtils.isEmpty(pattern)) {
    		pattern = "hhmm";
    	}
    	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
    
    //yyyyMMddHHmmss
    public static String getToday(String pattern) {
    	if(StringUtils.isEmpty(pattern)) {
    		pattern = "yyyyMMdd";
    	}
    	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
    
    public static int getYear() {
    	int yyyy;
    	LocalDate currentDate = LocalDate.now();
    	yyyy = currentDate.getYear();
    	return yyyy;
    }
    
    public static int getMonth() {
    	int mm;
    	LocalDate currentDate = LocalDate.now();
    	mm = currentDate.getMonthValue();
    	return mm;
    }
    
    
    public static String getCheckDateString(String timeStr, int substrIdx) {
    	String strTemp;
    	
    	if(timeStr == null) {
    		strTemp = "";
    	}else {
    		if(substrIdx > timeStr.length()) substrIdx = timeStr.length();
    		strTemp = timeStr;
    		strTemp = strTemp.substring(0, substrIdx);
    		strTemp = strTemp.replaceAll("-", "");
    		strTemp = strTemp.replaceAll(":", "");
    		strTemp = strTemp.replaceAll(" ", "");
    	}
    	return strTemp;
    }
    
    public static Map<String, String> getTimeMapyyyyMMddHHmmss(String timeStr){
    	Map<String, String> timeMap = new HashMap<String, String>();
    	
    	String tmpTimeStr = getCheckDateString(timeStr, timeStr.length());
    	if(tmpTimeStr == null || tmpTimeStr.length() < 8) tmpTimeStr = "20000101000000";
    	if(tmpTimeStr.length() >= 8) {
    		timeMap.put("yyyy", tmpTimeStr.substring(0,4));
    		timeMap.put("MM", tmpTimeStr.substring(4,6));
    		timeMap.put("dd", tmpTimeStr.substring(6,8));
    	}
    	if(tmpTimeStr.length() >= 10) {
    		timeMap.put("HH", tmpTimeStr.substring(8,10));
    	}
    	if(tmpTimeStr.length() >= 12) {
    		timeMap.put("mm", tmpTimeStr.substring(10,12));
    	}
    	if(tmpTimeStr.length() >= 14) {
    		timeMap.put("ss", tmpTimeStr.substring(12,14));
    	}
    	
    	return timeMap;
    }
    
    
//    public static String 
//
    
    //공백 제거
//     public String getChkNullReplace(String str){
//        String strTmp;
//
//        if(str == null){
//            strTmp = "";
//        }else{
//            str = str.replaceAll(" ","");
//            strTmp = str;
//        }
//
//        return strTmp.trim();
//    }
    
    
//
//    public String getDate(String nation, String date_type){
//        if(date_type == null) return null;
//        Calendar calendar = Calendar.getInstance();
//
//        String nation_code = "Asia/Seoul";
//
//        if(nation.equals("SIN")) nation_code = "Asia/Singapore";
//        else if(nation.equals("CHN")) nation_code = "Asia/Shanghai";
//    
//        SimpleTimeZone timezone=new SimpleTimeZone(9*60*60*1000,nation_code);
//        calendar = Calendar.getInstance(timezone);
//        Date date=calendar.getTime();
//    
//        SimpleDateFormat sdf = new SimpleDateFormat(date_type,Locale.KOREA);
//        sdf.setTimeZone(timezone);
//
//        return sdf.format(date);
//    }
//
//    public String getDate(String nation, String date_type,int prevday,int nextday) throws Exception{
//        if(date_type == null) return null;
//        Calendar calendar = Calendar.getInstance();
//
//        String nation_code = "Asia/Seoul";
//
//        if(nation.equals("SIN")) nation_code = "Asia/Singapore";
//        else if(nation.equals("CHN")) nation_code = "Asia/Shanghai";
//    
//        SimpleTimeZone timezone=new SimpleTimeZone(9*60*60*1000,nation_code);
//        calendar = Calendar.getInstance(timezone);
//
//        if(prevday != 0){
//            calendar.add(Calendar.DATE, prevday);
//        }else if(nextday != 0){
//            calendar.add(Calendar.DATE, nextday);
//        } 
//
//        Date date=calendar.getTime();
//    
//        SimpleDateFormat sdf = new SimpleDateFormat(date_type,Locale.KOREA);
//        sdf.setTimeZone(timezone);
//
//        return sdf.format(date);
//    }
//
    
	public static String getDebugDate(String nation, String date_type) {
		if (date_type == "")
			date_type = "yyyy-MM-dd HH:mm:ss";
		Calendar calendar = Calendar.getInstance();

		String nation_code = "Asia/Seoul";

		if (nation.equals("SIN"))
			nation_code = "Asia/Singapore";
		else if (nation.equals("CHN"))
			nation_code = "Asia/Shanghai";

		SimpleTimeZone timezone = new SimpleTimeZone(9 * 60 * 60 * 1000, nation_code);
		calendar = Calendar.getInstance(timezone);
		Date date = calendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(date_type, Locale.KOREA);
		sdf.setTimeZone(timezone);

		return sdf.format(date);
	}
//
//    public String getFileSize(int file_size){
//        String f1 = "";
//        double d_size = file_size;
//        NumberFormat formatter = new DecimalFormat("0.00");
//
//        if(d_size < 1000){
//            f1 = formatter.format(d_size) +" Byte";
//        }else if(d_size < 1000000){
//            f1 = formatter.format(d_size/1000) +" KB";
//        }else if(file_size < 1000000000){
//            f1 = formatter.format(d_size/1000000) +" MB";
//        }else{
//            f1 = formatter.format(d_size/1000000000) +" GB";
//        }
//
//        return f1;
//   }
//
//    public String getFileExt(String filename){
//        String f1 = "";
//        int dotindex = filename.lastIndexOf(".");
//        if (dotindex == -1){
//            return "";
//        }
//        f1 = filename.substring(dotindex);
//        if(f1 != null) f1 = f1.toLowerCase();
//
//        return f1;
//    }
//
//    public String getConvertLink(String src){
//        if(src == null) return "";
//
//        if(src.length() > 7){
//            if(!src.substring(0,7).equals("http://")){
//                src = "http://" + src;
//            }
//        }else if( src.length() > 8){
//            if(!src.substring(0,8).equals("https://")){
//                src = "https://" + src;
//            }
//        }else{
//            src = "http://" + src;
//        }
//
//        return src;
//    }
//
//    public String getXmlFormat(String src){
//        if(src == null) return "";
//
//        src = src.replaceAll("&","&amp;");
//        src = src.replaceAll("<","&lt;");
//        src = src.replaceAll(">","&gt;");
//        src = src.replaceAll("\"","&quot;");
//        src = src.replaceAll("\'","&apos;");
//        src = src.replaceAll("<","&lt;");
//        src = src.replaceAll("<","&lt;");
//
//        return src;
//    }
//
//    public String getGoUrl(String msg,String url,String parent){
//        String ret_str = "";
//
//        if(parent == null || parent.equals("")) parent = "SELF";
//        if(msg == null ) msg = "";
//
//        ret_str = "<script language=\"Javascript\">\n";
//
//        if(!msg.equals("")){
//            ret_str += "    alert(\"" + msg + "\");\n";
//        }
//
//        if(parent.equals("SELF")){
//            ret_str += "    document.location.href = \"" + url + "\";\n";
//        }else if(parent.equals("OPENER")){
//            ret_str += "    opener.location.reload();\n";
//            ret_str += "    self.close();\n";
//        }else if(parent.equals("PARENT")){
//            ret_str += "    parent.location.replace(\"" + url + "\");\n";
//        }else if(parent.equals("PARENTRELOAD")){
//            ret_str += "    parent.location.reload();\n";
//        }else if(parent.equals("PARENTCLOSE")){
//            ret_str += "    parent.location.href = \"" + url + "\";\n";
//            ret_str += "    self.close();\n";
//        }else if(parent.equals("NOMOVE")){
//
//        }else if(parent.equals("BACK")){
//            ret_str += "    window.history.back(-1);\n";
//        }else if(parent.equals("CLOSE")){
//            ret_str += "    self.close();\n";
//        }
//
//        ret_str += "</script>\n";
//
//        return ret_str;
//    }
//
//    public String getReadClobData(Reader reader) throws IOException {
//        StringBuffer data = new StringBuffer();
//        char[] buf = new char[1024];
//        int cnt = 0;
//
//        if(null != reader){
//            while((cnt = reader.read(buf)) != -1){
//                data.append(buf, 0, cnt);
//            }
//        }
//
//        return data.toString();
//    }
//
//    public String getWeeklyData(int num){
//        String week_date = "";
//        String start_date = "";
//        String end_date = "";
//        int Week_num = 0;
//
//        if(num == 1) Week_num = 6;
//        else Week_num = num - 2;
//        
//        DecimalFormat df = new DecimalFormat("00");
//        Calendar currentCalendar = Calendar.getInstance();
//
//        currentCalendar.add(currentCalendar.DATE, -Week_num);
//
//        String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
//        String strMonth = df.format(currentCalendar.get(Calendar.MONTH) + 1);
//        String strDay = df.format(currentCalendar.get(Calendar.DATE));
//
//        //strMonth = "12";
//        //strDay = "31";
//
//        start_date = strYear + "/" + strMonth + "/" + strDay;
//
//        currentCalendar.set(Calendar.YEAR, Integer.parseInt(strYear));
//        currentCalendar.set(Calendar.MONTH, Integer.parseInt(strMonth) - 1);
//        currentCalendar.set(Calendar.DATE, Integer.parseInt(strDay));
//
//        currentCalendar.add(currentCalendar.DATE, +6);
//
//        String strYear1 = Integer.toString(currentCalendar.get(Calendar.YEAR));
//        String strMonth1 = df.format(currentCalendar.get(Calendar.MONTH) + 1);
//        String strDay1 = df.format(currentCalendar.get(Calendar.DATE));
//
//        end_date = strYear1 + "/" + strMonth1 + "/" + strDay1;
//
//        week_date = start_date + " ~ " + end_date;
//
//        return week_date;
//    }
//
    public static String[] getDiffDays(String fromDate, String toDate){ //며칠 전만 계산됨
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        try{
            cal.setTime(sdf.parse(fromDate));
        }catch(Exception e){
        }

        int count = getDiffDayCount(fromDate, toDate);

        cal.add(Calendar.DATE, -1);

        ArrayList<String> list = new ArrayList<String>();

        for(int i=0; i<=count; i++){
            cal.add(Calendar.DATE, 1);

            list.add(sdf.format(cal.getTime()));
        }

        String[] result = new String[list.size()];

        list.toArray(result);

        return result;
    }
//
    
    public static int getDiffDayCount(String fromDate, String toDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try{
            return (int) ((sdf.parse(toDate).getTime() - sdf.parse(fromDate).getTime()) / 1000 / 60 / 60 / 24);
        }catch(Exception e){
            return 0;
        }
    }
    
    public static int getDiffDay(LocalDate criteria, LocalDate comparison) {
    	return (int)ChronoUnit.DAYS.between(criteria, comparison);
    }
    //TODO : getDiffDay로 getDiffDayCount 교체
    public static int getDiffDayCount(String fromDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar time = Calendar.getInstance();
		String toDate = sdf.format(time.getTime());

        try{
            return (int) ((sdf.parse(toDate).getTime() - sdf.parse(fromDate).getTime()) / 1000 / 60 / 60 / 24);
        }catch(Exception e){
            return 0;
        }
    }
    
    public static String getDiffDayString(String fromDate) { //몇 시간, 며칠 전만 계산됨
    	String diffStr = "";
    	
    	Map<String, String> fromMap = getTimeMapyyyyMMddHHmmss(fromDate);
    	Map<String, String> todayMap = getTimeMapyyyyMMddHHmmss(getToday("yyyMMddHHmmss"));
    	
    	String fromStr = fromMap.get("yyyy") + fromMap.get("MM") + fromMap.get("dd");
    	int diffDay = getDiffDayCount(fromStr);
    	
    	if(diffDay < 1) { //아직 하루가 안 지났으면
    		int diffHour = Integer.valueOf(todayMap.get("HH")) - Integer.valueOf(fromMap.get("HH"));
    		if(diffHour < 1) {
    			int diffMinute = Integer.valueOf(todayMap.get("mm")) - Integer.valueOf(fromMap.get("mm"));
    			diffStr = diffMinute + "분 전";
    		}else {
    			diffStr = diffHour + "시간 전";
    		}
    	}else {
    		diffStr = diffDay + "일 전";
    	}
    	
    	if(diffStr.indexOf('-') > -1) {
    		diffStr = "알 수 없음";
    	}
    	
    	return diffStr;
    }
    
    /*
	  	D:/10WEB_HOME/JSP_HOME/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/lotte/zcommonfiles/scrtmp/<-?????
		/zcommonfiles/kiosk/TST/K000001.jpg    <--
     */
    public String setMoveFile(String oldfile,String newfile,boolean isdel){
        String strret = "FAIL";

        try{
            InputStream stream = new FileInputStream(new File(oldfile));
            OutputStream bos = new FileOutputStream(newfile);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1){
               bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();
            if(isdel == true){
                File f = new File(oldfile);
                f.delete();
            }

            strret = "SUCC";
        }catch(Exception exception){
            System.out.println("[COMM CommUtil] setMoveFile Exception  : " + exception.getMessage());
            strret = "[COMM CommUtil] setMoveFile Exception  : " + exception.getMessage();
        }

        return strret;
    }
//
//    public String setDeleteFile(String name, String path){
//        String strret = "FAIL";
//
//        try{
//            String file_name = name;
//            String file_dir = path;
//
//            File file = new File(file_dir + file_name);
//
//            if(file.exists()){
//                file.delete();
//                strret = "SUCC";
//            }
//        }catch(Exception exception){
//            System.out.println("[COMM CommUtil] setDeleteFile Exception  : " + exception.getMessage());
//        }
//
//        return strret;
//    }
//
//    public String setResizeImage(String rename, String target_name, int i_width, int i_height) {
//      String strret = "FAIL";
//      BufferedImage src, dest;
//      ImageIcon icon;
//
//      try{
//          if(fileCopy(target_name,rename)){
//
//              src = ImageIO.read(new File(rename)); // 해당이미지 가져오기
//              int width = src.getWidth();
//              int height = src.getHeight();
//
//              if(width > i_width){
//                  float widthRatio = i_width / (float) width;
//                  width = (int) (width * widthRatio);
//                  height = (int) (height * widthRatio);
//              }
//
//              if(height > i_height){
//                  float heightRatio = i_height / (float) height;
//                  width = (int) (width * heightRatio);
//                  height = (int) (height * heightRatio);
//              }
//
//              dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
//
//              Graphics2D g = dest.createGraphics(); 
//              AffineTransform at = AffineTransform.getScaleInstance((double) width / src.getWidth(),(double) height / src.getHeight());
//
//              g.drawRenderedImage(src, at);
//              icon = new ImageIcon(dest);
//
//              Image i = icon.getImage();
//              BufferedImage bi = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_RGB);
//              Graphics2D g2 = bi.createGraphics();
//
//              g2.drawImage(i, 0, 0, null); 
//              g2.dispose();
//
//              ImageIO.write(bi, "jpg", new File(rename));
//
//              strret = "SUCC";
//           }
//      }catch(Exception exception){
//          System.out.println("[COMM CommUtil] setResizeImage Exception  : " + exception.getMessage());
//      }
//
//      return strret;
//    }
//
//     public boolean fileCopy(String inFileName, String outFileName) {
//        boolean result = false;
//        try {
//           FileInputStream fis = new FileInputStream(inFileName);
//           FileOutputStream fos = new FileOutputStream(outFileName);
//           
//           int data = 0;
//           while((data=fis.read())!=-1) {
//              fos.write(data);
//           }
//
//           fis.close();
//           fos.close();
//
//           result = true;
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//        return result;
//     }
//
//
//
//     public String getMovieToImg(String oldfilename, String oldbasepath,String newFilename,String ffmpegPath){
//
//        //oldfilename = 들어올 파일 이름
//        //oldbasepath= 실제 파일이 있는 경로
//        //newFilename = 파일을 보낼 경로.
//        //ffmpegPath = ffmpeg.exe 파일이 있는 경로.
//
//        String retstr = "FAIL";
//        String outputName = oldfilename; 
//        File fResult = new File(oldbasepath + System.getProperty("file.separator") + outputName);
//        String fImg = newFilename;
//        String[] cmdLine = new String[]{ffmpegPath, "-i", fResult.getPath(), "-vcodec", "mjpeg", "-vframes", "1", "-an", "-f", "rawvideo", fImg};  //SWF 이미지변환
//
//        // 프로세스 속성을 관리하는 ProcessBuilder 생성.
//        ProcessBuilder pb = new ProcessBuilder(cmdLine);
//
//        pb.redirectErrorStream(true);
//
//        Process p = null;
//
//
//        try {
//            // 프로세스 작업을 실행함.
//            p = pb.start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            p.destroy();
//            return null; 
//        }
//
//        exhaustInputStream(p.getInputStream());   // 자식 프로세스에서 발생되는 인풋 스트림 소비시킴;;
//
//        try {
//            p.waitFor();// p의 자식 프로세스의 작업이 완료될 동안 p를 대기시킴
//        } catch (InterruptedException e) {
//            p.destroy();
//        }
//
//        // 정상 종료가 되지 않았을 경우
//        if (p.exitValue() != 0){ 
//            return retstr;
//        }
//
//        // 변환을 하는 중 에러가 발생하여 파일의 크기가 0일 경우
//        if (fResult.length() == 0){ 
//            return retstr ;
//        }
//
//            return retstr;
//     }
//
//     private void exhaustInputStream(final InputStream is) {
//          // InputStream.read() 에서 블럭상태에 빠지기 때문에 따로 쓰레드를 구현하여 스트림을 소비한다.
//                new Thread() {
//                    public void run() {
//                        try {
//                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                            String cmd;
//                            while((cmd = br.readLine()) != null) { // 읽어 들일 라인이 없을 때까지 계속 반복);
//                            }
//                        }catch(IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//     }
//
//     public String[] getNowFirst_LastDay(){//첫번째 년,월,일  마지막 년,월,일을 구하는 메소드
//         String[] result = new String[2];
//         Date today = new Date();  
//         Calendar calendar = Calendar.getInstance();  
//         calendar.setTime(today);  
//         calendar.add(Calendar.MONTH, 1);  
//         calendar.set(Calendar.DAY_OF_MONTH, 1);  
//         calendar.add(Calendar.DATE, -1);  
//         Date lastDayOfMonth = calendar.getTime();  
//         DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//         Calendar calendar2 = Calendar.getInstance();  
//         calendar2.setTime(today);  
//         calendar2.add(Calendar.MONTH, 0);  
//         calendar2.set(Calendar.DAY_OF_MONTH, 1);  
//         calendar2.add(Calendar.DATE, 0); 
//         Date lastDayOfMonth2 = calendar2.getTime();  
//         String lastDay = sdf.format(lastDayOfMonth);
//         String firstDay = sdf.format(lastDayOfMonth2);
//         result[0] = firstDay;
//         result[1] = lastDay;
//         return result;
//     }
//    
//     public String[] getNowFirst_LastWeek(){//해당 일의 월요일 ~ 일요일 구간(주)
//         String[] result = new String[2];
//         Calendar today = Calendar.getInstance();
//         String s = (today.get(Calendar.YEAR)) + "";
//         String m = (today.get(Calendar.MONTH) + 1) + "";
//         String e = (today.get(Calendar.DATE)) + "";
//         if((today.get(Calendar.MONTH) + 1) < 10){ m = "0"+m;}
//         if((today.get(Calendar.DATE)) < 10){ e = "0"+e;}		   
//         String dateString = s + "" + m + "" + e;
//       
//         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 
//         Date date = null; 
//         try { 
//            date = simpleDateFormat.parse(dateString); 
//         }
//         catch (Exception ee) { 
//            System.out.println("잘못된 문자열이네요"); 
//         } 
//         Calendar cal = Calendar.getInstance(Locale.KOREA); 
//         cal.setTime(date); 
//         cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK)); 
//         String startday = simpleDateFormat.format(cal.getTime()); 
//         cal.setTime(date); 
//         cal.add(Calendar.DATE, 7 - cal.get(Calendar.DAY_OF_WEEK)); 
//         String endday =  simpleDateFormat.format(cal.getTime());
//         result[0] = startday;
//         result[1] = endday;
//         return result;
//     }
    ////////////////////////////////////////////////////////////////////////////////
    // String 관련
    public static int[] getString2IntArray(String str, String token) {
    	if(StringUtils.trimToNull(str) == null || StringUtils.trimToNull(token) == null) {
    		return new int[] {};
    	}
    	String[] strs = str.split(token);
    	int[] numArr = new int[strs.length];
    	
    	for(int i = 0; i < strs.length; i++) {
    		try {
    			int num = Integer.parseInt(strs[i]);
    			numArr[i] = num;
    		}catch(Exception e) {
    			logger.error("ERROR ===> [{}] :: {} ", e.toString(), e.getMessage());
    			return new int[] {};
    		}
    	}
    	return numArr;
    }
    
    public static String getRandomStr(int length) {
		 
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			String randomStr = String.valueOf((char) ((rnd.nextInt(26)) + 97)); // 아스키코드 97~122  a-z
			sb.append(randomStr);
		}
		
		return sb.toString();
    }
    
	////////////////////////////////////////////////////////////////////////
	// Web 관련
	// Controller에서 Json 응답 만들 때 사용하는 함수
	@SuppressWarnings("unchecked")
	public static JSONObject getJsonResponse(String code, String msg, String red) {
		JSONObject jRes = new JSONObject();
		jRes.put("code", code);
		jRes.put("msg", msg);
		jRes.put("red", red);

		return jRes;
	}
	
	public static String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	// CommUtil.tld
	public static String getArray2JsonStr(String key, String[] arr) {
		
		String tempKey = key;
		String[] tempArr = arr;
		if(key == null) {
			tempKey = "";
		}
		if(arr == null) {
			tempArr = new String[] {""};
		}
		
		JSONArray jarr = new JSONArray();
		jarr.addAll(Arrays.asList(tempArr));
		
		JSONObject jobj = new JSONObject();
		jobj.put(tempKey, jarr);
		
		return jobj.toJSONString().replace("\"", "'");
	}
	
	public static String getArray2JsonStr(String key, String[][] arr) {
		
		String tempKey = key;
		String[][] tempArr = arr;
		if(key == null) {
			tempKey = "";
		}
		if(arr == null) {
			tempArr = new String[][] {{""}};
		}
		
		JSONArray jarr = new JSONArray();
		for(String[] arr1 : tempArr) {
			JSONArray jarrTmp = new JSONArray();
			jarrTmp.addAll(Arrays.asList(arr1));
			jarr.add(jarrTmp);
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put(tempKey, jarr);
		
		return jobj.toJSONString().replace("\"", "'");
	}
	
	/**
	 * <pre>
	 * getAbbreviate("123456789",8) = "12345..."
	 * getAbbreviate("ABCD",4) = "A..."
	 * getAbbreviate("ABCD",2) = "A..."
	 * getAbbreviate("A",3) = "A"
	 * </pre>
	 * @param str
	 * @param maxWidth
	 * @return
	 */
	public static String getAbbreviate(String str,int maxWidth) {
		if(maxWidth < 4) {
			maxWidth = 4;
		}
		return StringUtils.abbreviate(str, maxWidth);
	}
	
	//ex) yyyymmdd -> yyyy-mm-dd
	public static String getYYYY_MM_DD(String date) {
		return LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE).toString();
	}
	
	public static String getImgOrDefault(String path, String fileName) {
		if(StringUtils.isBlank(path) || StringUtils.isBlank(fileName)) {
			return "/images/icon.gif";
		}
		return path + "/" + fileName;
	}
	
	public static String getNanoRemovedTime(Timestamp time) {
		if(time == null) return "-";
		String timeStr = time.toString();
		return timeStr.substring(0, timeStr.lastIndexOf('.'));
	}
	
}
