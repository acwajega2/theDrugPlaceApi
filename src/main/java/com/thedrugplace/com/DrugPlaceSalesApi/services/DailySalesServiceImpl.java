package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailySales;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.branch.BranchPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.BranchSalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.DailySalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.staff.BestPerformingStaffDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailySalesService;
import com.thedrugplace.com.DrugPlaceSalesApi.mappers.DailySalesMapper;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.DailySalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailySalesServiceImpl implements DailySalesService {

    @Autowired
    private DailySalesRepository dailySalesRepository;

    @Autowired
    private DailySalesMapper dailySalesMapper; // Inject the DailySalesMapper

    @Override
    public DailySalesDto createDailySales(DailySalesDto dailySalesDto) {
        try {
            var dailySales = dailySalesMapper.dtoToEntity(dailySalesDto);

            if (dailySalesRepository.findByTransactionReference(dailySalesDto.getTransactionReference()) != null) {
                throw new CustomException("Sale already recorded");
            }
            return dailySalesMapper.entityToDto(dailySalesRepository.save(dailySales));
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to create daily sales: " + ex.getMessage());
        }
    }

    @Override
    public DailySalesDto getDailySalesById(Long salesId) {
        try {
            Optional<DailySales> salesOptional = dailySalesRepository.findById(salesId);
            return salesOptional.map(dailySalesMapper::entityToDto).orElse(null);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get daily sales by ID: " + ex.getMessage());
        }
    }

    @Override
    public List<DailySalesDto> getAllDailySales() {
        try {
            List<DailySales> dailySalesList = dailySalesRepository.findAll();
            return dailySalesList.stream().map(dailySalesMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get all daily sales: " + ex.getMessage());
        }
    }

    @Override
    public DailySalesDto updateDailySales(Long salesId, DailySalesDto dailySalesDto) {
        try {
            if (dailySalesRepository.existsById(salesId)) {
                var dailySales = dailySalesMapper.dtoToEntity(dailySalesDto);
                return dailySalesMapper.entityToDto(dailySalesRepository.save(dailySales));
            }
            return null;
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to update daily sales: " + ex.getMessage());
        }
    }

    @Override
    public void deleteDailySales(Long salesId) {
        try {
            dailySalesRepository.deleteById(salesId);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to delete daily sales: " + ex.getMessage());
        }
    }

    @Override
    public List<BranchSalesDto> getBranchSalesWithStaffDetailsOrderByDateDesc() {
        try {
            List<Object[]> results = dailySalesRepository.findBranchSalesWithStaffDetailsOrderByDateDesc();
            return results.stream()
                    .map(result -> {
                        Branch branch = (Branch) result[0];
                        Staff staff = (Staff) result[1];
                        Date date = (Date) result[2];
                        double totalSales = (double) result[3];
                        return new BranchSalesDto(branch, staff, date, totalSales);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get BranchSales with Staff Details ordered by date: " + ex.getMessage());
        }
    }

    @Override
    public List<DailySalesDto> getSalesByStaffPhoneNumberWithBranchDetails(String phoneNumber) {
        try {
            List<DailySales> salesList = dailySalesRepository.findSalesByStaffPhoneNumberWithBranchDetails(phoneNumber);
            return salesList.stream().map(dailySalesMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get daily sales by staff phone number: " + ex.getMessage());
        }

    }

    @Override
    public List<DailySalesDto> getSalesByBranchCode(String branchCode) {
        try {
            List<DailySales> salesList = dailySalesRepository.findSalesByBranchCode(branchCode);
            return salesList.stream().map(dailySalesMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get daily sales by staff Branch Code: " + ex.getMessage());
        }
    }

    @Override
    public List<BranchPerformanceDto> getBranchPerformanceByMonthAndYear() {
        try {
            List<Object[]> results = dailySalesRepository.findBranchPerformanceByMonthAndYear();
            return results.stream()
                    .map(result -> {
                        Branch branch = (Branch) result[0];
                        int year = (int) result[1];
                        int month = (int) result[2];
                        double totalSales = (double) result[3];
                        double averageSales = (double) result[4];
                        return new BranchPerformanceDto(branch, year, month, totalSales, averageSales);
                    })
                    .collect(Collectors.toList());

        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get Branch Sales Performance: " + ex.getMessage());
        }
    }


    @Override
    public List<BestPerformingStaffDto> getBestPerformingStaffByMonthAndYear() {
        try {
            List<Object[]> results = dailySalesRepository.findBestPerformingStaffByMonthAndYear();
            return results.stream()
                    .map(result -> {
                        Staff staff = (Staff) result[0];
                        int year = (int) result[1];
                        int month = (int) result[2];
                        double totalSales = (double) result[3];
                        return new BestPerformingStaffDto(staff, year, month, totalSales);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get getBestPerformingStaffByMonthAndYear: " + ex.getMessage());
        }
    }
}
