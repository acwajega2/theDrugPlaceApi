package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchDto;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Branch entities and BranchDto data transfer objects.
 */
@Component
public class BranchMapper {
    private final ModelMapper modelMapper = new ModelMapper();


    private final StaffSalaryMapper staffSalaryMapper = new StaffSalaryMapper();

    /**
     * Converts a Branch entity to a BranchDto.
     *
     * @param branch The Branch entity to be converted.
     * @return The corresponding BranchDto.
     */
    public BranchDto entityToDto(Branch branch) {
        BranchDto branchDto = new BranchDto();
        branchDto.setBranchCode(branch.getBranch_code());
        branchDto.setBranchLocation(branch.getBranch_location());
        branchDto.setBranchName(branch.getBranch_name());
        branchDto.setBranchManagerId(branch.getBranch_manager_id());
        branchDto.setBranchOpeningDate(branch.getBranch_opening_date().toString());
        branchDto.setStaffSalaries(branch.getStaffSalaries().stream().map(staffSalaryMapper::entityToDto).collect(Collectors.toList()));
        return branchDto;
    }

    /**
     * Converts a BranchDto to a Branch entity.
     *
     * @param branchDto The BranchDto to be converted.
     * @return The corresponding Branch entity.
     */
    public Branch dtoToEntity(BranchDto branchDto) throws ParseException {
        Branch branch = new Branch();
        branch.setBranch_code("");
        branch.setBranch_location(branchDto.getBranchLocation());
        branch.setBranch_manager_id(branchDto.getBranchManagerId());
        branch.setBranch_name(branchDto.getBranchName());
        branch.setBranch_code(branchDto.getBranchCode());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        branch.setBranch_opening_date(dateFormat.parse(branchDto.getBranchOpeningDate()));
        branch.setCreated_at(LocalDateTime.now());
        return branch;
    }
}
