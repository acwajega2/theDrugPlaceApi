package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.BranchRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * Mapper class for converting between Staff entities and StaffDto data transfer objects.
 */
@Component
public class StaffMapper {
    @Autowired
    private BranchRepository branchRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a Staff entity to a StaffDto.
     *
     * @param staff The Staff entity to be converted.
     * @return The corresponding StaffDto.
     */
    public StaffDto entityToDto(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setStaffEmail(staff.getStaff_email());
        staffDto.setStaffRole(staff.getStaff_role());
        staffDto.setStaffName(staff.getStaff_name());
        staffDto.setHireDate(staff.getHire_date().toString());
        staffDto.setStaffPhone(staff.getStaff_phone());
        staffDto.setBranchCode(staff.getBranch().getBranch_code());
        return staffDto;
    }

    /**
     * Converts a StaffDto to a Staff entity.
     *
     * @param staffDto The StaffDto to be converted.
     * @return The corresponding Staff entity.
     */
    public Staff dtoToEntity(@NotNull StaffDto staffDto) throws ParseException {
        Staff staff = new Staff();
        staff.setStaff_email(staffDto.getStaffEmail());
        staff.setStaff_name(staffDto.getStaffName());
        staff.setStaff_phone(staffDto.getStaffPhone());
        staff.setCreated_at(LocalDateTime.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        staff.setHire_date(dateFormat.parse(staffDto.getHireDate()));
        staff.setBranch(getBranchByCode(staffDto.getBranchCode()));
        staff.setStaff_role(staffDto.getStaffRole());

        return staff;
    }

    private Branch getBranchByCode(String branchCode) {

        Branch branch = branchRepository.findBranchByBranch_code(branchCode);
        if (branch == null) {
            throw new CustomException("Branch not found");
        }
        return branch;

    }
}
