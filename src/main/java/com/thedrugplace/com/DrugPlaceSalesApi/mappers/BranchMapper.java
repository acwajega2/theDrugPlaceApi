package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchDto;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * Mapper class for converting between Branch entities and BranchDto data transfer objects.
 */
@Component
public class BranchMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a Branch entity to a BranchDto.
     *
     * @param branch The Branch entity to be converted.
     * @return The corresponding BranchDto.
     */
    public BranchDto entityToDto(Branch branch) {
        return modelMapper.map(branch, BranchDto.class);
    }

    /**
     * Converts a BranchDto to a Branch entity.
     *
     * @param branchDto The BranchDto to be converted.
     * @return The corresponding Branch entity.
     */
    public Branch dtoToEntity(BranchDto branchDto) throws ParseException {
        Branch branch = new Branch();
        branch.setBranchCode("");
        branch.setBranchLocation(branchDto.getBranchLocation());
        branch.setBranchManagerId(branchDto.getBranchManagerId());
        branch.setBranchName(branchDto.getBranchName());
        branch.setBranchCode(branchDto.getBranchCode());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        branch.setBranchOpeningDate(dateFormat.parse(branchDto.getBranchOpeningDate()));
        branch.setCreatedAt(LocalDateTime.now());
        return branch;
    }
}
