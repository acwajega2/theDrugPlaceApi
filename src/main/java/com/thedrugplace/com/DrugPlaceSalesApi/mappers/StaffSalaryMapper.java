package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.StaffSalary;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffSalaryDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.BranchService;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffService;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.BranchRepository;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.StaffRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Mapper class for converting between StaffSalary entities and StaffSalaryDto data transfer objects.
 */
@Component
public class StaffSalaryMapper {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private BranchService branchService;
    @Autowired
    private BranchMapper branchMapper;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a StaffSalary entity to a StaffSalaryDto.
     *
     * @param staffSalary The StaffSalary entity to be converted.
     * @return The corresponding StaffSalaryDto.
     */
    public StaffSalaryDto entityToDto(StaffSalary staffSalary) {
        StaffSalaryDto staffSalaryDto = new StaffSalaryDto();

        if (staffSalary != null) {
            staffSalaryDto.setAmount(staffSalary.getAmount());

            Branch branch = staffSalary.getBranch();
            if (branch != null) {
                staffSalaryDto.setBranchCode(branch.getBranch_code());
            }

            staffSalaryDto.setPaymentDate(staffSalary.getPayment_date());
            staffSalaryDto.setTransactionReference(staffSalary.getTransaction_reference());
            staffSalaryDto.setStaffPhone(staffSalary.getStaff().getStaff_phone());
        }

        return staffSalaryDto;
    }

    /**
     * Converts a StaffSalaryDto to a StaffSalary entity.
     *
     * @param staffSalaryDto The StaffSalaryDto to be converted.
     * @return The corresponding StaffSalary entity.
     */
    public StaffSalary dtoToEntity(@NotNull StaffSalaryDto staffSalaryDto) throws ParseException {
        StaffSalary staffSalary = new StaffSalary();
        staffSalary.setStaff(getStaffBYPhone(staffSalaryDto.getStaffPhone()));
        staffSalary.setBranch(getBranchByCode(staffSalaryDto.getBranchCode()));
        staffSalary.setAmount(staffSalaryDto.getAmount());
        staffSalary.setPayment_date(staffSalaryDto.getPaymentDate());
        staffSalary.setTransaction_reference(staffSalaryDto.getTransactionReference());
        return staffSalary;
    }

    private Branch getBranchByCode(String branchCode) {

        Branch branch = branchRepository.findBranchByBranch_code(branchCode);
        if (branch == null) {
            throw new CustomException("Branch not found");
        }
        return branch;

    }

    private Staff getStaffBYPhone(String staffPhone) {

        Staff staff = staffRepository.findByStaffPhone(staffPhone);
        if (staff == null) {
            throw new CustomException("staff not found");
        }
        return staff;

    }
}
