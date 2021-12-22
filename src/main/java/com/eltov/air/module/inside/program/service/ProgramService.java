package com.eltov.air.module.inside.program.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eltov.air.core.DTO.SearchDTO;
import com.eltov.air.core.code.ExceptionCode;
import com.eltov.air.core.config.CommConfig;
import com.eltov.air.core.exception.ExceptionWrapper;
import com.eltov.air.core.util.file.FileProcess;
import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.DTO.ProgramFileDTO;
import com.eltov.air.module.inside.program.mapper.ProgramMapper;
import com.eltov.air.module.inside.user.DTO.UserDTO;

@Service
public class ProgramService {

	private final CommConfig commConfig;
	private final ProgramMapper programMapper;
	private static final Logger logger = LoggerFactory.getLogger(ProgramService.class);
	
	@Autowired
	public ProgramService(ProgramMapper programMapper, CommConfig commConfig) {
		this.programMapper = programMapper;
		this.commConfig = commConfig;
	}
	
	/**************************************************
	*	get program list 
	**************************************************/
	public List<ProgramDTO> getPrograms(SearchDTO searchDto, UserDTO user){
		List<ProgramDTO> programs = programMapper.getPrograms(searchDto, 1); 
		logger.info("getPrograms => {}.", "search data : NAME - " + searchDto.getSearchName() + " / " + "EXT - " + searchDto.getSearchSect());
		logger.info("getPrograms => {}.", "Searched program count : " + programs.size());
		return programs;
	}

	/**************************************************
	*	regist program 
	**************************************************/
	public void registProgram(FileProcess fileProcess, ProgramDTO programDto, UserDTO user) throws Exception {
		// company branch 코드 dir 만들기
		String detailPath = commConfig.getGlPathProgram() + "/" + "cpnCode" + "/" + "brnCode";	
		programDto.setRegId(1);
		programDto.setBrnId(1);
		registProgramDB(programDto);
		ProgramFileDTO programFileDto = new ProgramFileDTO();
		programFileDto.setProgramId(programDto.getProgramId());
		programFileDto.setFileVer("1.0");
		fileProcess.fileUploadProcess(commConfig.getGlPathFileRoot(), detailPath, programFileDto);
		registProgramFileDB(programFileDto);
	}
	
	/**************************************************
	*	regist program insert db
	**************************************************/
	private void registProgramDB(ProgramDTO programDto) {
		int row = programMapper.registProgramDB(programDto);
		if(row > 0) {
			logger.info("registProgramDB => {}.", "Registered Program ID : " + programDto.getProgramId());
		}else {
			logger.error("registProgramDB => {}.", "Failed to save program.");
		}
	}
	
	/**************************************************
	*	regist program file info insert db
	**************************************************/
	private void registProgramFileDB(ProgramFileDTO programFileDTO) {
		int row = programMapper.registProgramFileDB(programFileDTO);
		if(row > 0) {
			logger.info("registProgramFileDB => {}.", "Successed to save program file data.");
		}else {
			logger.error("registProgramFileDB => {}.", "Failed to save program file data.");
		}
	}
	
	/**************************************************
	*	getting program for update
	**************************************************/
	public ProgramDTO getProgramToUpdate(ProgramDTO programDto, UserDTO user) {
		programDto.setBrnId(1);
		ProgramDTO program = programMapper.getProgramToUpdate(programDto); 
		if(program == null) 			{ logger.info("getProgramToUpdate => {}.", "This program has no information."); }
		logger.info("getProgramToUpdate => {}.", "Update ready program info : " + program);
		List<ProgramFileDTO> programFileList = programMapper.getProgramToUpdateFile(programDto);
		if(programFileList.size() < 1) 	{ logger.info("getProgramToUpdate => {}.", "This program has no file."); }
		logger.info("getProgramToUpdate => {}.", "Program file list count : " + programFileList.size());
		logger.info("getProgramToUpdate => {}.", "Program file list : " + programFileList);
		program.setProgramFileList(programFileList);
		return program; 
	}
	
	/**************************************************
	*	program info update
	**************************************************/
	public void updateProgram(ProgramDTO programDto, UserDTO user) {
		programDto.setUpdId(1);
		programDto.setBrnId(1);
		updateProgramDB(programDto);
	}
	
	/**************************************************
	*	update program info insert db
	**************************************************/
	private void updateProgramDB(ProgramDTO programDto) {
		programMapper.updateProgramDB(programDto); 
	}
	
	/**************************************************
	*	update program info insert db
	**************************************************/
	public void updateProgramVer(FileProcess fileProcess, ProgramFileDTO programFileDto, UserDTO user) throws Exception {
		// company branch 코드 dir 만들기
		String detailPath = commConfig.getGlPathProgram() + "/" + "cpnCode" + "/" + "brnCode";
		fileProcess.fileUploadProcess(commConfig.getGlPathFileRoot(), detailPath, programFileDto);
		setProgramVerUseN(programFileDto.getProgramId());
		updateProgramVerDB(programFileDto);
//		programDto.setRegId(1);
//		programDto.setBrnId(1);
//		getOrgProgramFileVer();
//		updateProgramVerDB(programDto);
//		ProgramFileDTO programFileDto = new ProgramFileDTO();
//		programFileDto.setProgramId(programDto.getProgramId());
//		programFileDto.setFileVer("1.0");
//		fileProcess.fileUploadProcess(commConfig.getGlPathFileRoot(), detailPath, programFileDto);
//		registProgramFileDB(programFileDto);
	}
	
	/**************************************************
	*	select program version for use
	**************************************************/
	private void setProgramVerUseN(int programId) {
		programMapper.setProgramVerUseN(programId); 
	}
	
	/**************************************************
	*	for use program version insert db
	**************************************************/
	public void updateProgramVerDB(ProgramFileDTO programFileDto) {
		programMapper.updateProgramVerDB(programFileDto); 
	}
	
	public void getOrgProgramFileVer() {
		
	}
	
	/**************************************************
	*	change program version for use
	**************************************************/
	public void changeProgramVer(int fileId, int programId) {
		programMapper.setProgramVerUseN(programId); 
		programMapper.changeProgramVer(fileId); 
	}
	
	/**************************************************
	*	delete program
	**************************************************/
	public void deleteProgram(List<Integer> delIds, UserDTO user) {
		FileProcess fileProcess = new FileProcess();
//		user.setBrn_id(1);
		// program info list to delete 
		for(int delId : delIds) {
			// program info`s DB status update to 'D' 
			deleteProgramDB(delId, 1);
			// get program file
			List<ProgramFileDTO> programFiles = getProgramFileToDelete(delId, 1);
			System.out.println(programFiles);
			for(ProgramFileDTO file : programFiles) {
				// file delete db
				deleteProgramFileDB(file);
				// file delete
				try {
					fileProcess.fileDeleteProcess(commConfig.getGlPathFileRoot(), file.getFilePath(), file.getFileName());
				} catch (com.eltov.air.core.exception.ExceptionWrapper e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void deleteProgramDB(int delId, int brnId) {
		programMapper.deleteProgramDB(delId, brnId); 
	}

	public List<ProgramFileDTO> getProgramFileToDelete(int delId, int brnId) {
		List<ProgramFileDTO> programFiles = programMapper.getProgramFileToDelete(delId, brnId);
		return programFiles;
	}
	
	public void deleteProgramFileDB(ProgramFileDTO file) {
		 programMapper.deleteProgramFileDB(file);
	}
	
	public Map<String, Object> programDownload(int fileId, UserDTO user) throws ExceptionWrapper {
		
		Map<String, Object> resMap = new HashMap<>();
		
		//user.getBrn_id()
		//1. 파일 정보 가져옴
		ProgramFileDTO fileInfo = this.getProgramFileToDownload(fileId, 1);
		
		String fileFullPath = commConfig.getGlPathFileRoot() + fileInfo.getFilePath() + "/" + fileInfo.getFileName();
		String fileRealname = fileInfo.getFileRealname();

		//2. 유효한 파일인지 검사
		File programFile = new File(fileFullPath);
		if(!programFile.isFile()) {
			throw new ExceptionWrapper(ExceptionCode.E204_FILE_NOT_FOUND, new Exception(fileFullPath));
		}
		
		//3. 담아서 던진다
		resMap.put("file", fileFullPath);
		resMap.put("fileName", fileRealname);
		
		return resMap;
	}
	
	public ProgramFileDTO getProgramFileToDownload(int downloadId, int brnId) {
		return programMapper.getProgramFileToDownload(downloadId, brnId);
	}
}
