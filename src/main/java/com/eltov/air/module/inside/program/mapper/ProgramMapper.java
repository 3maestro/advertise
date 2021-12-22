package com.eltov.air.module.inside.program.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eltov.air.core.DTO.SearchDTO;
import com.eltov.air.module.inside.program.DTO.ProgramDTO;
import com.eltov.air.module.inside.program.DTO.ProgramFileDTO;

@Mapper
public interface ProgramMapper {

	public List<ProgramDTO> getPrograms(@Param("search") SearchDTO searchDto,@Param("brnId")int brnId);
	public int registProgramDB(@Param("pg")ProgramDTO programDto);
	public int registProgramFileDB(@Param("file")ProgramFileDTO programFileDTO);
	
	public ProgramDTO getProgramToUpdate(@Param("pg") ProgramDTO programDto);
	public List<ProgramFileDTO> getProgramToUpdateFile(@Param("pg") ProgramDTO programDto);
	public void updateProgramDB(@Param("pg") ProgramDTO programDto);
	public void setProgramVerUseN(@Param("programId") int ProgramId);
	public void updateProgramVerDB(@Param("file")ProgramFileDTO programFileDTO);
	public void changeProgramVer(@Param("fileId") int fileId);
	
	public void deleteProgramDB(@Param("programId") int delId, @Param("brnId")int brnId);
	public List<ProgramFileDTO> getProgramFileToDelete(@Param("programId") int delId, @Param("brnId")int brnId);
	public void deleteProgramFileDB(@Param("file")ProgramFileDTO programFileDTO);
	
	public ProgramFileDTO getProgramFileToDownload(@Param("programId") int downloadId, @Param("brnId")int brnId);
	
}
