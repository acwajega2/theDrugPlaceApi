package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.StaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.StaffService;
import com.thedrugplace.com.DrugPlaceSalesApi.mappers.StaffMapper;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.StaffRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffMapper staffMapper; // Inject the StaffMapper

    @Override
    public StaffDto createStaff(StaffDto staffDto) {
        try {
            var staff = staffMapper.dtoToEntity(staffDto);
            if (staffRepository.findByStaffPhone(staff.getStaff_phone()) != null) {
                throw new CustomException("Staff already saved");
            }
            staffRepository.save(staff);
            return staffDto;
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to create staff: " + ex.getMessage());
        }
    }

    @Override
    public StaffDto getStaffById(Long staffId) {
        try {
            Optional<Staff> staffOptional = staffRepository.findById(staffId);
            return staffOptional.map(staffMapper::entityToDto).orElse(null);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get staff by ID: " + ex.getMessage());
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        try {
            List<Staff> staffList = staffRepository.findAll();
            return staffList.stream().map(staffMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get all staff: " + ex.getMessage());
        }
    }

    @Override
    public StaffDto updateStaff(Long staffId, StaffDto staffDto) {
        try {
            if (staffRepository.existsById(staffId)) {
                var staff = staffMapper.dtoToEntity(staffDto);
                return staffMapper.entityToDto(staffRepository.save(staff));
            }
            return null;
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to update staff: " + ex.getMessage());
        }
    }

    @Override
    public void deleteStaff(Long staffId) {
        try {
            staffRepository.deleteById(staffId);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to delete staff: " + ex.getMessage());
        }
    }

    @Override
    public List<StaffDto> getStaffByBranch_code(String branchCode) {
        List<Staff> staffList = staffRepository.findByBranch_BranchCode(branchCode);
        return staffList.stream().map(staffMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Staff getStaffByUsername(String username) {
        return staffRepository.findByUsername(username);
    }


    @Override
    public List<StaffDto> searchStaffByPhoneNumber(String staffPhone) {
        List<Staff> staffList = staffRepository.findAllByStaffPhone(staffPhone);
        return staffList.stream().map(staffMapper::entityToDto).collect(Collectors.toList());
    }

}