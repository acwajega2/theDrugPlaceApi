package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.StaffSalary;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffSalaryDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffSalaryService;
import com.thedrugplace.com.DrugPlaceSalesApi.mappers.StaffSalaryMapper;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.StaffSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffSalaryServiceImpl implements StaffSalaryService {
    @Autowired
    private StaffSalaryRepository staffSalaryRepository;
    @Autowired
    private StaffSalaryMapper staffSalaryMapper;

    @Override
    public StaffSalaryDto createStaffSalary(StaffSalaryDto staffSalaryDto) {
        try {
            StaffSalary staffSalary = staffSalaryMapper.dtoToEntity(staffSalaryDto);
            if (staffSalaryRepository.findByTransactionReference(staffSalaryDto.getTransactionReference()) !=null){
                throw new CustomException("Staff salary already recorded");
            }
            return staffSalaryMapper.entityToDto(staffSalaryRepository.save(staffSalary));

        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to createStaffSalary: " + ex.getMessage());
        }
    }

    @Override
    public List<StaffSalaryDto> getStaffSalariesByStaffId(Long staffId) {
        try {
            List<StaffSalary> staffSalaries = staffSalaryRepository.findAllByStaff_StaffId(staffId);
            return staffSalaries.stream()
                    .map(staffSalaryMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to  getStaffSalariesByStaffId : " + ex.getMessage());
        }
    }
    @Override
    public List<StaffSalaryDto> getStaffSalariesByBranchId(Long branchId) {
        try {
            List<StaffSalary> staffSalaries = staffSalaryRepository.findAllByBranch_BranchId(branchId);
            return staffSalaries.stream()
                    .map(staffSalaryMapper::entityToDto)
                    .collect(Collectors.toList());
        }catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to  getStaffSalariesByStaffId : " + ex.getMessage());
        }

    }


    @Override
    public List<StaffSalaryDto> getStaffSalariesByStaffPhoneNumber(String staffPhone) {
        try{
            List<StaffSalary> staffSalaries = staffSalaryRepository.findAllByStaff_staffPhone(staffPhone);
            return staffSalaries.stream()
                    .map(staffSalaryMapper::entityToDto)
                    .collect(Collectors.toList());

        }catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to  getStaffSalariesByStaffId : " + ex.getMessage());
        }

    }


}
