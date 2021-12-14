package com.eltov.air.module.inside.program.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.eltov.air.module.inside.program.DTO.ProgramDTO;

@Mapper
public interface ProgramMapper {

	public void registProgram();
	public void registProgramDb(ProgramDTO programDto);
}
