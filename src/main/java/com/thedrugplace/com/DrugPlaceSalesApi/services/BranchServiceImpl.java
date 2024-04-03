package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailyExpenses;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchProfitDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.BranchService;
import com.thedrugplace.com.DrugPlaceSalesApi.mappers.BranchMapper;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.BranchRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper; // Inject the BranchMapper

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    @Transactional
    public BranchDto createBranch(BranchDto branchDto) {
        try {
            // Set the generated branch code in the DTO
         //   branchDto.setBranchCode(generateRandomBranchCode());
            Branch branch = branchMapper.dtoToEntity(branchDto);

             if (branchRepository.findBranchByBranchName(branch.getBranch_name()) !=null){
                 throw new CustomException("Branch already saved");
             }

            String branchName = branchDto.getBranchName();
            if (branchName == null || branchName.trim().isEmpty()) {
                throw new CustomException("Branch name cannot be null or empty");
            }
            branch.setDailyExpenses(new ArrayList<>());
            branch.setDailySales(new ArrayList<>());
            branch.setStaffSalaries(new ArrayList<>());
            System.out.println(branch);
            branchRepository.save(branch);

            return branchDto;
        } catch (DataAccessException | ParseException ex) {
            throw new CustomException("Failed to create a branch: " + ex.getMessage());
        }
    }

    // Generate a random branch code using Apache Commons Lang
    private String generateRandomBranchCode() {
        // Generate a random alphanumeric code of a specified length
        return RandomStringUtils.randomAlphanumeric(8); // You can adjust the length as needed
    }

    @Override
    public BranchDto getBranchById(Long branchId) {
        try {
            Optional<Branch> branchOptional = branchRepository.findById(branchId);
            return branchOptional.map(branchMapper::entityToDto).orElse(null);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get branch by ID: " + ex.getMessage());
        }
    }

    @Override
    public List<BranchDto> getAllBranches() {
        try {
            List<Branch> branches = branchRepository.findAll();
            return branches.stream().map(branchMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get all branches: " + ex.getMessage());
        }
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branchDto) {
        try {
            if (branchRepository.existsById(branchId)) {
                Branch branch = branchMapper.dtoToEntity(branchDto);
                return branchMapper.entityToDto(branchRepository.save(branch));
            } else {
                return null;
            }
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to update branch: " + ex.getMessage());
        }
    }

    @Override
    public void deleteBranch(Long branchId) {
        try {
            branchRepository.deleteById(branchId);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to delete branch: " + ex.getMessage());
        }
    }

    @Override
    public List<BranchProfitDto> getProfitableBranchesByMonthAndYear() {
        try {
            List<Object[]> results = branchRepository.findBranchProfitsByMonthAndYear();
            return results.stream()
                    .map(result -> {
                        Branch branch = (Branch) result[0];
                        int year = (int) result[1];
                        int month = (int) result[2];
                        double netProfit = (double) result[3];
                        return new BranchProfitDto(branch, year, month, netProfit);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to delete branch: " + ex.getMessage());
        }
    }
}
