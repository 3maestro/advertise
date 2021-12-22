package com.eltov.air.core.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.eltov.air.core.code.ExceptionCode;
import com.eltov.air.core.config.CommConfig;
import com.eltov.air.core.exception.ExceptionWrapper;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.coobird.thumbnailator.Thumbnails;

@Component
public class FileUtil {
	
	private final CommConfig config;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	private String FFPMEG_PATH;
	private String FFPROBE_PATH;
	private static final String TEMP_PREFIX = "tmp_";
	public boolean m_b_timeresult = true; //Local Varriable
    public String  m_play_time = "0"; //Local Varriable
    public String  m_mov_width = "";
    public String  m_mov_height = "";
    
    @Autowired
    public FileUtil(CommConfig config) {
    	this.config = config;
    }
    
    @PostConstruct
    public void init() {
    	this.FFPMEG_PATH = config.getGlPathFfmpeg();
    	this.FFPROBE_PATH = config.getGlPathFfprobe();
    }
	
	public static String ab() {
		System.out.println("123123");
		return "123";
	}
    
	public static MultipartFile getCheckNullFile(MultipartFile file) throws ExceptionWrapper {
		if (file == null || file.isEmpty() || file.getSize() <= 0 || file.getOriginalFilename() == null || file.getOriginalFilename().equals("")) {
			throw new ExceptionWrapper(ExceptionCode.E204_FILE_NOT_FOUND, new Exception("업로드할 파일이 존재하지 않음"));
		}
		return file;
	}

	
	public static Map<String, MultipartFile> getCheckNullFile(Map<String, MultipartFile> fileMap) throws ExceptionWrapper {
		if (fileMap == null || fileMap.isEmpty() || fileMap.size() == 0) {
			throw new ExceptionWrapper(ExceptionCode.E204_FILE_NOT_FOUND, new Exception("업로드할 파일이 존재하지 않음"));
		}
		return fileMap;
	}

	public static File setSaveFile(MultipartFile file, String absolutePath, String fileName) throws ExceptionWrapper {
		File uploadFolder = new File(absolutePath);
		if (!uploadFolder.isDirectory()) {
			uploadFolder.mkdirs();
		}

		File targetFile = new File(absolutePath, fileName);
		try {
			file.transferTo(targetFile);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExceptionWrapper(ExceptionCode.E202_FILE_SAVE_FAIL, e);
		}
		
		return targetFile;
	}
	
	public static File setSaveThumbnail(File file, String absolutePath, String fileName) throws ExceptionWrapper {
		File thumbnail = new File(absolutePath, fileName);
		try {
			Thumbnails.of(file).size(100, 100).toFile(thumbnail);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExceptionWrapper(ExceptionCode.E202_FILE_SAVE_FAIL, e);
		}
		return thumbnail;
	}
	
	public static MultipartFile getFileFirstOne(Map<String, MultipartFile> fileMap) throws ExceptionWrapper {
		MultipartFile file = fileMap.values().stream().findFirst().get();
		return file;
	}
	
	public void setDelete(String uploadPath, String fileName) throws ExceptionWrapper {
		try {
			Path filePath = Paths.get(config.getGlPathFileRoot(), uploadPath, fileName);
			Files.delete(filePath);
		} catch (Exception e) {
			throw new ExceptionWrapper(ExceptionCode.E205_FILE_DELETE_FAIL, e);
		}
	}
	public static void setDelete(Path filePath) throws ExceptionWrapper {
		try {
			Files.delete(filePath);
		} catch (Exception e) {
			throw new ExceptionWrapper(ExceptionCode.E205_FILE_DELETE_FAIL, e);
		}
	}
	
	public void setUpdateFile(String uploadPath, String origName, String newName, MultipartFile file) throws ExceptionWrapper {
		Path origPath = Paths.get(config.getGlPathFileRoot(), uploadPath, origName);
		Path tmpPath = Paths.get(config.getGlPathFileRoot(), uploadPath, TEMP_PREFIX + origName);
		File newFile = null;
		
		//0. 임시 파일 저장
		try {
			tmpPath = Files.copy(origPath, tmpPath, StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) { //임시파일을 저장하지 못하면 새 파일을 저장하다가 예외 발생 시 원본 파일을 보장할 수 없으므로 throw Exception하여 롤백
			throw new ExceptionWrapper(ExceptionCode.E202_FILE_SAVE_FAIL, new Exception("임시파일 저장 실패 "));
		}
		//1. 원본파일 삭제
		try {
			setDelete(origPath);
		}catch(ExceptionWrapper e) {
			logger.warn("POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
		}
		try {
			//2. 새 파일 추가
			setSaveFile(file, uploadPath, origName);
			try {
				//3.1 성공: 임시파일 삭제
				setDelete(uploadPath, TEMP_PREFIX + origName);
			}catch(ExceptionWrapper e) {
				//임시파일 삭제 실패시
				logger.warn("POINT::{} ===> [{}] :: {} ", e.getErrorPoint(), e.getErrorCode(), e.getErrorCause());
			}
		}catch(Exception e) {
			// 3.2 실패: 원본파일 복원 
			try {
				origPath = Paths.get(config.getGlPathFileRoot(), uploadPath, origName);
				Files.move(tmpPath, origPath, StandardCopyOption.REPLACE_EXISTING);
				//원본 파일 복원 후 DB도 롤백하여 파일 수정이 최종 실패하였음을 알림
				throw new ExceptionWrapper(ExceptionCode.E208_FILE_UPDATE_FAIL, new Exception("파일 저장 실패"));
			}catch(IOException ex) {
				//새 파일도 없고 원본파일도 없어진 경우
				throw new ExceptionWrapper(ExceptionCode.E000_UNCHECKED_ERROR, new Exception("새 파일 저장 실패 - 원본파일 복원 실패"), "수정에 실패하였으나 원본 파일을 복원할 수 없습니다. 관리자에게 문의하십시오.");
			}
			
		}
	}
	
	public File setSaveThumbnailForVideo(String absolutePath, String mainFileName, String thumbFileName) throws Exception {
		
		File convertedVideoFile = new File(absolutePath, mainFileName);

		String[] cmdLine = new String[] { FFPMEG_PATH, "-ss", "2", "-y", "-i", convertedVideoFile.getPath(), "-vcodec",
				"mjpeg", "-vframes", "3", "-an", "-f", "rawvideo", FilenameUtils.concat(absolutePath, thumbFileName) }; // SWF
																													// 이미지변환
		// 동영상 플레이 타임을 구하는 구간 시작
        try{
            ProcessBuilder reading = new ProcessBuilder(FFPMEG_PATH,"-i",convertedVideoFile.getPath(), convertedVideoFile.getPath());
            final Process prc2 = reading.start();
//            new Thread() {
//                public void run() {//Im might be a 

                    try{
                    	Scanner sc = new Scanner(prc2.getErrorStream());
                        Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
                        Pattern durPattern2 = Pattern.compile("(?<=Stream #0:0)[^,]*");
                        String dur = sc.findWithinHorizon(durPattern, 0);
                        if (dur == null){throw new RuntimeException("Couldn't parse duration.");}
                        String[] hms = dur.split(":");
                        double totalSecs = Integer.parseInt(hms[0])*3600 + Integer.parseInt(hms[1])*60 + Double.parseDouble(hms[2]);
                        m_play_time = totalSecs + "";
                        m_b_timeresult = false;

                        String resolution = sc.findWithinHorizon(durPattern2, 0);
                        if (resolution == null){throw new RuntimeException("Couldn't parse resolution.");}

                    }catch(Exception e){
                        m_b_timeresult = false;
                        throw e;
                    }finally {
                    	prc2.getErrorStream().close();
                    	prc2.getInputStream().close();
                    	prc2.getOutputStream().close();
					}
//                }
//            }.start();
            prc2.waitFor();
        }catch(Exception e){
            m_b_timeresult = false;
			e.printStackTrace();
            throw e;
        }

        int count = 1;

        try{

            while(m_b_timeresult && count <= 100000){  //리턴값을 맞추기 위해서 프로세싱하는 동안 슬립, 최대 count 100까지
                try{
                    Thread.sleep(10);
                    count++;
                }catch(Exception e){			
                	e.printStackTrace();
                    e.printStackTrace();
                    break;
                }
            }

            //파일 변환 프로세싱 시작
            ProcessBuilder pb = new ProcessBuilder(cmdLine);
            pb.redirectErrorStream(true);
            Process p = null;

            try{
                p = pb.start();
                p.waitFor();
            }catch(Exception e){
                p.destroy();
            }

            setExhaustInputStream(p.getInputStream());

            try{
                p.waitFor();
            } catch (InterruptedException e){
            	p.destroy();
        	}

            // 정상 종료가 되지 않았을 경우 
            if (p.exitValue() != 0){
                m_play_time = "0";
                throw new ExceptionWrapper(ExceptionCode.E401_PROCESS_ABNORMAL_TERMINATION, new Exception("ffmpeg가 정상 종료되지 않음"));
            }

            // 변환을 하는 중 에러가 발생하여 파일의 크기가 0일 경우
            if (convertedVideoFile.length() == 0){
                m_play_time = "0"; 
                throw new ExceptionWrapper(ExceptionCode.E207_FILE_CONVERT_ERROR, new Exception("썸네일 변환 실패"));
            }
            
            logger.debug("is Alive ?????????? {}", p.isAlive());
        }catch(Exception e){
            m_play_time = "0";
            throw e;
        }
        
        File thumFile = new File(absolutePath, thumbFileName);
        
        if(!thumFile.isFile() || thumFile.length() <= 0) {
        	throw new ExceptionWrapper(ExceptionCode.E202_FILE_SAVE_FAIL, new Exception("썸네일 저장 실패"));
        }
        
        return thumFile;
	}
	
	private static void setExhaustInputStream(final InputStream is) {
		// InputStream.read() 에서 블럭상태에 빠지기 때문에 따로 쓰레드를 구현하여 스트림을 소비한다.
		new Thread() {
			public void run() {
				BufferedReader br;
				try {
					br = new BufferedReader(new InputStreamReader(is));
					String cmd;
					while ((cmd = br.readLine()) != null) { // 읽어 들일 라인이 없을 때까지 계속 반복);
					}
					br.close();
                    is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public int getMoviePlayTime(String fileFullName) throws ExceptionWrapper {
		int playTime = 0;

		try {
			FFprobe ffprobe = new FFprobe(FFPROBE_PATH);
			FFmpegProbeResult probeResult = ffprobe.probe(fileFullName); // 동영상 경로
			FFmpegFormat format = probeResult.getFormat();
			double second = format.duration; // 초단위

			playTime = (int)second;
		} catch (Exception e) {
			throw new ExceptionWrapper(ExceptionCode.E402_FFMPEG_FAIL, e);
		}

		return playTime;
	}

}
